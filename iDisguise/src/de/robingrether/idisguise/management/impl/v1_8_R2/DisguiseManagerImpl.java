package de.robingrether.idisguise.management.impl.v1_8_R2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.minecraft.server.v1_8_R2.Entity;
import net.minecraft.server.v1_8_R2.Packet;
import net.minecraft.server.v1_8_R2.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R2.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R2.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R2.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R2.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_8_R2.PacketPlayOutPlayerInfo.PlayerInfoData;
import net.minecraft.server.v1_8_R2.EntityPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_8_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.authlib.GameProfile;

import de.robingrether.idisguise.disguise.Disguise;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.PlayerDisguise;
import de.robingrether.idisguise.management.DisguiseManager;
import de.robingrether.idisguise.management.DisguiseMap;
import de.robingrether.idisguise.management.DisguiseMapLegacy;
import de.robingrether.idisguise.management.GhostFactory;
import de.robingrether.idisguise.management.PacketHelper;
import de.robingrether.idisguise.management.PlayerHelper;

public class DisguiseManagerImpl extends DisguiseManager {
	
	private Field fieldListInfo;
	private DisguiseMap disguiseMap = new DisguiseMap();
	
	public DisguiseManagerImpl() {
		try {
			fieldListInfo = PacketPlayOutPlayerInfo.class.getDeclaredField("b");
			fieldListInfo.setAccessible(true);
		} catch(Exception e) {
		}
	}
	
	public Packet<?>[] getSpawnPacket(Player player) {
		Packet<?>[] packetSpawn;
		Disguise disguise = getDisguise(player);
		if(disguise == null) {
			packetSpawn = new Packet<?>[1];
			packetSpawn[0] = new PacketPlayOutNamedEntitySpawn(((CraftPlayer)player).getHandle());
		} else {
			packetSpawn = (Packet<?>[])PacketHelper.instance.getPackets(player, disguise);
		}
		return packetSpawn;
	}
	
	protected Packet<?> getPlayerInfoPacket(Player player) {
		PacketPlayOutPlayerInfo packetInfo = null;
		Disguise disguise = getDisguise(player);
		if(disguise == null) {
			packetInfo = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, ((CraftPlayer)player).getHandle());
		} else if(disguise instanceof PlayerDisguise) {
			packetInfo = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[0]);
			try {
				List<PlayerInfoData> list = (List<PlayerInfoData>)fieldListInfo.get(packetInfo);
				list.add(packetInfo.new PlayerInfoData((GameProfile)PlayerHelper.instance.getGameProfile(((PlayerDisguise)disguise).getName()), ((CraftPlayer)player).getHandle().ping, ((CraftPlayer)player).getHandle().playerInteractManager.getGameMode(), CraftChatMessage.fromString(((PlayerDisguise)disguise).getPlayerListName())[0]));
			} catch(Exception e) {
			}
		}
		return packetInfo;
	}
	
	protected Packet<?> getDestroyPacket(Player player) {
		return new PacketPlayOutEntityDestroy(player.getEntityId());
	}
	
	private synchronized void sendPacket(Player player, Packet<?>... packets) {
		if(packets == null) {
			return;
		}
		try {
			Method method = ((CraftPlayer)player).getHandle().playerConnection.getClass().getDeclaredMethod("sendPacketFromPlugin", Packet.class);
			for(Packet<?> packet : packets) {
				method.invoke(((CraftPlayer)player).getHandle().playerConnection, packet);
			}
		} catch(Exception e) {
		}
	}
	
	public void sendPacketLater(final Player player, final Object packet, long delay) {
		BukkitRunnable runnable = new BukkitRunnable() {
			public void run() {
				if(packet instanceof Packet) {
					sendPacket(player, (Packet<?>)packet);
				} else {
					sendPacket(player, (Packet<?>[])packet);
				}
			}
		};
		runnable.runTaskLater(Bukkit.getPluginManager().getPlugin("iDisguise"), delay);
	}
	
	public synchronized void disguise(OfflinePlayer offlinePlayer, Disguise disguise) {
		if(offlinePlayer.isOnline()) {
			Player player = offlinePlayer.getPlayer();
			Disguise oldDisguise = disguiseMap.getDisguise(player.getUniqueId());
			if(oldDisguise == null) {
				PacketPlayOutPlayerInfo packetPlayerInfoRemove = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, ((CraftPlayer)player).getHandle());
				for(Player observer : Bukkit.getOnlinePlayers()) {
					if(observer == player) {
						continue;
					}
					sendPacket(observer, packetPlayerInfoRemove);
				}
			} else if(oldDisguise instanceof PlayerDisguise) {
				PacketPlayOutPlayerInfo packetPlayerInfoRemove = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[0]);
				try {
					List<PlayerInfoData> list = (List<PlayerInfoData>)fieldListInfo.get(packetPlayerInfoRemove);
					list.add(packetPlayerInfoRemove.new PlayerInfoData((GameProfile)PlayerHelper.instance.getGameProfile(((PlayerDisguise)oldDisguise).getName()), ((CraftPlayer)player).getHandle().ping, ((CraftPlayer)player).getHandle().playerInteractManager.getGameMode(), CraftChatMessage.fromString(((PlayerDisguise)oldDisguise).getPlayerListName())[0]));
				} catch(Exception e) {
				}
				player.setDisplayName(player.getName());
				for(Player observer : Bukkit.getOnlinePlayers()) {
					if(observer == player) {
						continue;
					}
					sendPacket(observer, packetPlayerInfoRemove);
				}
				if(oldDisguise.getType().equals(DisguiseType.GHOST)) {
					GhostFactory.instance.removeGhost(player);
				}
			}
			disguiseMap.putDisguise(player.getUniqueId(), disguise);
			if(disguise instanceof PlayerDisguise) {
				Packet<?> packetPlayerInfoAdd = getPlayerInfoPacket(player);
				for(Player observer : Bukkit.getOnlinePlayers()) {
					if(observer == player) {
						continue;
					}
					sendPacket(observer, packetPlayerInfoAdd);
				}
				player.setDisplayName(((PlayerDisguise)disguise).getName());
				if(((PlayerDisguise)disguise).isGhost()) {
					GhostFactory.instance.addPlayer(((PlayerDisguise)disguise).getName());
					GhostFactory.instance.addGhost(player);
				}
			}
			Packet<?> packetDestroy = getDestroyPacket(player);
			Packet<?>[] packetSpawn = getSpawnPacket(player);
			for(Player observer : player.getWorld().getPlayers()) {
				if(observer == player) {
					continue;
				}
				sendPacket(observer, packetDestroy);
				sendPacketLater(observer, packetSpawn, 40L);
			}
			updateAttributes(player);
		} else {
			disguiseMap.putDisguise(offlinePlayer.getUniqueId(), disguise);
		}
	}
	
	public synchronized Disguise undisguise(OfflinePlayer offlinePlayer) {
		if(offlinePlayer.isOnline()) {
			Player player = offlinePlayer.getPlayer();
			Disguise disguise = disguiseMap.removeDisguise(player.getUniqueId());
			if(disguise == null) {
				return null;
			}
			Packet<?> packetPlayerInfoAdd = getPlayerInfoPacket(player);
			if(disguise instanceof PlayerDisguise) {
				try {
					PacketPlayOutPlayerInfo packetPlayerInfoRemove = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[0]);
					List<PlayerInfoData> list = (List<PlayerInfoData>)fieldListInfo.get(packetPlayerInfoRemove);
					list.add(packetPlayerInfoRemove.new PlayerInfoData((GameProfile)PlayerHelper.instance.getGameProfile(((PlayerDisguise)disguise).getName()), ((CraftPlayer)player).getHandle().ping, ((CraftPlayer)player).getHandle().playerInteractManager.getGameMode(), CraftChatMessage.fromString(((PlayerDisguise)disguise).getPlayerListName())[0]));
					for(Player observer : Bukkit.getOnlinePlayers()) {
						if(observer == player) {
							continue;
						}
						sendPacket(observer, packetPlayerInfoRemove);
						sendPacket(observer, packetPlayerInfoAdd);
					}
				} catch(Exception e) {
				}
				if(disguise.getType().equals(DisguiseType.GHOST)) {
					GhostFactory.instance.removeGhost(player);
				}
			} else {
				for(Player observer : Bukkit.getOnlinePlayers()) {
					if(observer == player) {
						continue;
					}
					sendPacket(observer, packetPlayerInfoAdd);
				}
			}
			Packet<?> packetDestroy = getDestroyPacket(player);
			Packet<?>[] packetSpawn = getSpawnPacket(player);
			if(disguise instanceof PlayerDisguise) {
				player.setDisplayName(player.getName());
			}
			for(Player observer : player.getWorld().getPlayers()) {
				if(observer == player) {
					continue;
				}
				sendPacket(observer, packetDestroy);
				sendPacketLater(observer, packetSpawn, 40L);
			}
			updateAttributes(player);
			return disguise;
		} else {
			return disguiseMap.removeDisguise(offlinePlayer.getUniqueId());
		}
	}
	
	public void undisguiseAll() {
		for(UUID player : disguiseMap.getPlayers()) {
			if(Bukkit.getPlayer(player) != null) {
				undisguise(Bukkit.getPlayer(player));
			} else {
				disguiseMap.removeDisguise(player);
			}
		}
	}
	
	public synchronized void updateAttributes(Player player, Player observer) {
		if(observer == player) {
			return;
		}
		Packet<?>[] packets = new Packet<?>[6];
		int entityId = player.getEntityId();
		Location location = player.getLocation();
		EntityEquipment equipment = player.getEquipment();
		packets[0] = new PacketPlayOutEntityEquipment(entityId, 0, CraftItemStack.asNMSCopy(equipment.getItemInHand()));
		packets[1] = new PacketPlayOutEntityEquipment(entityId, 1, CraftItemStack.asNMSCopy(equipment.getBoots()));
		packets[2] = new PacketPlayOutEntityEquipment(entityId, 2, CraftItemStack.asNMSCopy(equipment.getLeggings()));
		packets[3] = new PacketPlayOutEntityEquipment(entityId, 3, CraftItemStack.asNMSCopy(equipment.getChestplate()));
		packets[4] = new PacketPlayOutEntityEquipment(entityId, 4, CraftItemStack.asNMSCopy(equipment.getHelmet()));
		Entity entity = ((CraftPlayer)player).getHandle();
		packets[5] = new PacketPlayOutEntityHeadRotation(entity, (byte)(location.getYaw() * 256 / 360));
		sendPacketLater(observer, packets, 50L);
	}
	
	protected synchronized void updateAttributes(Player player) {
		for(Player observer : player.getWorld().getPlayers()) {
			if(observer == player) {
				continue;
			}
			updateAttributes(player, observer);
		}
	}
	
	public boolean isDisguised(OfflinePlayer offlinePlayer) {
		return disguiseMap.isDisguised(offlinePlayer.getUniqueId());
	}
	
	public Disguise getDisguise(OfflinePlayer offlinePlayer) {
		return disguiseMap.getDisguise(offlinePlayer.getUniqueId());
	}
	
	public int getOnlineDisguiseCount() {
		int count = 0;
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(isDisguised(player)) {
				count++;
			}
		}
		return count;
	}
	
	public Set<OfflinePlayer> getDisguisedPlayers() {
		Set<OfflinePlayer> set = new HashSet<OfflinePlayer>();
		for(UUID player : disguiseMap.getMap().keySet()) {
			set.add(Bukkit.getOfflinePlayer(player));
		}
		return set;
	}
	
	public Disguise removeDisguise(OfflinePlayer offlinePlayer) {
		return disguiseMap.removeDisguise(offlinePlayer.getUniqueId());
	}
	
	public Map getDisguises() {
		return disguiseMap.getMap();
	}
	
	public void updateDisguises(Map map) {
		if(!map.keySet().isEmpty()) {
			if(map.keySet().iterator().next() instanceof UUID) {
				disguiseMap = new DisguiseMap(map);
			} else if(map.keySet().iterator().next() instanceof String) {
				disguiseMap = new DisguiseMap(new DisguiseMapLegacy(map));
			}
		}
	}
	
}