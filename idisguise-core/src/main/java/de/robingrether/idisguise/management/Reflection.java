package de.robingrether.idisguise.management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.robingrether.idisguise.iDisguise;

public class Reflection {
	
	public static Class<?> GameProfile;
	public static Method GameProfile_getProfileId;
	
	public static Class<?> CraftPlayer;
	public static Method CraftPlayer_getHandle;
	public static Method CraftPlayer_getProfile;
	
	public static Class<?> CraftOfflinePlayer;
	public static Method CraftOfflinePlayer_getProfile;
	
	public static Class<?> CraftChatMessage;
	public static Method CraftChatMessage_fromString;
	public static Method CraftChatMessage_fromComponent;
	
	public static Class<?> EntityPlayer;
	public static Method EntityPlayer_getBukkitEntity;
	public static Field EntityPlayer_playerConnection;
	public static Field EntityPlayer_playerInteractManager;
	public static Method EntityPlayer_updateAbilities;
	
	public static Class<?> EntityHuman;
	public static Field EntityHuman_exp;
	public static Field EntityHuman_expTotal;
	public static Field EntityHuman_expLevel;
	
	public static Class<?> DataWatcherItem;
	public static Field DataWatcherItem_dataWatcherObject;
	
	public static Class<?> DataWatcherObject;
	public static Method DataWatcherObject_getId;
	
	public static Class<?> PacketPlayInUseEntity;
	public static Field PacketPlayInUseEntity_entityId;
	public static Method PacketPlayInUseEntity_getAction;
	public static Field PacketPlayInUseEntity_action;
	
	public static Class<?> EnumEntityUseAction;
	public static Field EnumEntityUseAction_ATTACK;
	
	public static Class<?> PacketPlayOutNamedEntitySpawn;
	public static Constructor<?> PacketPlayOutNamedEntitySpawn_new;
	public static Field PacketPlayOutNamedEntitySpawn_entityId;
	public static Field PacketPlayOutNamedEntitySpawn_profileId;
	public static Field PacketPlayOutNamedEntitySpawn_x;
	public static Field PacketPlayOutNamedEntitySpawn_y;
	public static Field PacketPlayOutNamedEntitySpawn_z;
	public static Field PacketPlayOutNamedEntitySpawn_yaw;
	public static Field PacketPlayOutNamedEntitySpawn_pitch;
	public static Field PacketPlayOutNamedEntitySpawn_dataWatcher;
	
	public static Class<?> PacketPlayOutSpawnEntityLiving;
	public static Constructor<?> PacketPlayOutSpawnEntityLiving_new;
	public static Field PacketPlayOutSpawnEntityLiving_entityId;
	public static Field PacketPlayOutSpawnEntityLiving_x;
	public static Field PacketPlayOutSpawnEntityLiving_y;
	public static Field PacketPlayOutSpawnEntityLiving_z;
	public static Field PacketPlayOutSpawnEntityLiving_yaw;
	public static Field PacketPlayOutSpawnEntityLiving_pitch;
	public static Field PacketPlayOutSpawnEntityLiving_dataWatcher;
	
	public static Class<?> PacketPlayOutPlayerInfo;
	public static Constructor<?> PacketPlayOutPlayerInfo_new;
	public static Field PacketPlayOutPlayerInfo_action;
	public static Field PacketPlayOutPlayerInfo_playerInfoList;
	public static Constructor<?> PacketPlayOutPlayerInfo_new_full;
	
	public static Class<?> PacketPlayOutBed;
	public static Field PacketPlayOutBed_entityId;
	
	public static Class<?> PacketPlayOutAnimation;
	public static Field PacketPlayOutAnimation_entityId;
	public static Field PacketPlayOutAnimation_animationType;
	
	public static Class<?> PacketPlayOutEntityMetadata;
	public static Constructor<?> PacketPlayOutEntityMetadata_new_empty;
	public static Constructor<?> PacketPlayOutEntityMetadata_new_full;
	public static Field PacketPlayOutEntityMetadata_entityId;
	public static Field PacketPlayOutEntityMetadata_metadataList;
	
	public static Class<?> PacketPlayOutEntity;
	public static Field PacketPlayOutEntity_entityId;
	public static Field PacketPlayOutEntity_deltaX;
	public static Field PacketPlayOutEntity_deltaY;
	public static Field PacketPlayOutEntity_deltaZ;
	public static Field PacketPlayOutEntity_yaw;
	public static Field PacketPlayOutEntity_pitch;
	public static Field PacketPlayOutEntity_isOnGround;
	
	public static Class<?> PacketPlayOutEntityLook;
	public static Class<?> PacketPlayOutRelEntityMove;
	public static Class<?> PacketPlayOutRelEntityMoveLook;
	
	public static Class<?> PacketPlayOutEntityTeleport;
	public static Constructor<?> PacketPlayOutEntityTeleport_new;
	public static Field PacketPlayOutEntityTeleport_entityId;
	public static Field PacketPlayOutEntityTeleport_x;
	public static Field PacketPlayOutEntityTeleport_y;
	public static Field PacketPlayOutEntityTeleport_z;
	public static Field PacketPlayOutEntityTeleport_yaw;
	public static Field PacketPlayOutEntityTeleport_pitch;
	public static Field PacketPlayOutEntityTeleport_isOnGround;
	
	public static Class<?> PacketPlayOutUpdateAttributes;
	public static Field PacketPlayOutUpdateAttributes_entityId;
	
	public static Class<?> PacketPlayOutNamedSoundEffect;
	public static Constructor<?> PacketPlayOutNamedSoundEffect_new;
	public static Field PacketPlayOutNamedSoundEffect_soundEffect;
	public static Field PacketPlayOutNamedSoundEffect_soundCategory;
	public static Field PacketPlayOutNamedSoundEffect_x;
	public static Field PacketPlayOutNamedSoundEffect_y;
	public static Field PacketPlayOutNamedSoundEffect_z;
	public static Field PacketPlayOutNamedSoundEffect_volume;
	public static Field PacketPlayOutNamedSoundEffect_pitch;
	
	public static Class<?> PlayerInfoData;
	public static Constructor<?> PlayerInfoData_new;
	public static Method PlayerInfoData_getProfile;
	public static Method PlayerInfoData_getPing;
	public static Method PlayerInfoData_getGamemode;
	public static Method PlayerInfoData_getDisplayName;
	
	public static Class<?> World;
	public static Method World_getEntityById;
	public static Method World_getDifficulty;
	public static Method World_getType;
	public static Method World_getSpawn;
	
	public static Class<?> Entity;
	public static Field Entity_world;
	public static Method Entity_setLocation;
	public static Method Entity_setEntityId;
	public static Method Entity_getDataWatcher;
	public static Method Entity_setCustomName;
	public static Method Entity_setCustomNameVisible;
	public static Method Entity_getBukkitEntity;
	public static Field Entity_CUSTOM_NAME;
	
	public static Class<?> EntityAgeable;
	public static Method EntityAgeable_setAge;
	
	public static Class<?> EntitySheep;
	public static Method EntitySheep_setColor;
	
	public static Class<?> EntityWolf;
	public static Method EntityWolf_setCollarColor;
	public static Method EntityWolf_setTamed;
	public static Method EntityWolf_setAngry;
	
	public static Class<?> EnumColor;
	public static Method EnumColor_fromColorIndex;
	
	public static Class<?> EntityCreeper;
	public static Method EntityCreeper_setPowered;
	
	public static Class<?> EntityEnderman;
	public static Method EntityEnderman_setCarried;
	
	public static Class<?> Block;
	public static Method Block_getById;
	public static Method Block_fromLegacyData;
	public static Method Block_getCombinedId;
	
	public static Class<?> EntityGuardian;
	public static Method EntityGuardian_setElder;
	
	public static Class<?> EntityHorse;
	public static Method EntityHorse_setType;
	public static Method EntityHorse_setVariant;
	public static Method EntityHorse_setHasChest;
	public static Field EntityHorse_inventoryChest;
	public static Method EntityHorse_setArmor;
	
	public static Class<?> EntityHorseAbstract;
	public static Field EntityHorseAbstract_inventoryChest;
	
	public static Class<?> EntityHorseChestedAbstract;
	public static Method EntityHorseChestedAbstract_setCarryingChest;
	
	public static Class<?> EnumHorseType;
	public static Method EnumHorseType_fromIndex;
	
	public static Class<?> InventorySubcontainer;
	public static Method InventorySubcontainer_setItem;
	
	public static Class<?> CraftItemStack;
	public static Method CraftItemStack_asNMSCopy;
	
	public static Class<?> EntityOcelot;
	public static Method EntityOcelot_setCatType;
	
	public static Class<?> EntityPig;
	public static Method EntityPig_setSaddle;
	
	public static Class<?> EntityRabbit;
	public static Method EntityRabbit_setRabbitType;
	
	public static Class<?> EntitySlime;
	public static Method EntitySlime_setSize;
	
	public static Class<?> EntitySkeleton;
	public static Method EntitySkeleton_setSkeletonType;
	
	public static Class<?> EntityVillager;
	public static Method EntityVillager_setProfession;
	
	public static Class<?> EntityZombie;
	public static Method EntityZombie_setBaby;
	public static Method EntityZombie_setVillager;
	public static Method EntityZombie_setVillagerType;
	
	public static Class<?> EntityZombieVillager;
	public static Method EntityZombieVillager_setProfession;
	
	public static Class<?> EntityBat;
	public static Method EntityBat_setAsleep;
	
	public static Class<?> EntityFallingBlock;
	
	public static Class<?> PacketPlayOutSpawnEntity;
	public static Constructor<?> PacketPlayOutSpawnEntity_new;
	public static Field PacketPlayOutSpawnEntity_x;
	public static Field PacketPlayOutSpawnEntity_y;
	public static Field PacketPlayOutSpawnEntity_z;
	
	public static Class<?> EntityItem;
	public static Method EntityItem_setItemStack;
	
	public static Class<?> EntityMinecartAbstract;
	public static Method EntityMinecartAbstract_setDisplayBlock;
	
	public static Class<?> MinecraftKey;
	public static Constructor<?> MinecraftKey_new;
	public static Method MinecraftKey_getName;
	
	public static Class<?> RegistryMaterials;
	public static Method RegistryMaterials_getKey;
	public static Method RegistryMaterials_getValue;
	
	public static Class<?> SoundEffect;
	public static Field SoundEffect_registry;
	
	public static Class<?> EntityArmorStand;
	public static Method EntityArmorStand_setArms;
	
	public static Class<?> PacketPlayOutCollect;
	public static Field PacketPlayOutCollect_entityId;
	
	public static Class<?> EnumSkeletonType;
	public static Method EnumSkeletonType_fromIndex;
	
	public static Class<?> EnumZombieType;
	public static Method EnumZombieType_fromIndex;
	
	public static Class<?> EntityLlama;
	public static Method EntityLlama_setVariant;
	
	public static Class<?> EntityParrot;
	public static Method EntityParrot_setVariant;
	
	public static Class<?> PacketPlayOutScoreboardTeam;
	public static Constructor<?> PacketPlayOutScoreboardTeam_new;
	public static Field PacketPlayOutScoreboardTeam_teamName;
	public static Field PacketPlayOutScoreboardTeam_displayName;
	public static Field PacketPlayOutScoreboardTeam_prefix;
	public static Field PacketPlayOutScoreboardTeam_suffix;
	public static Field PacketPlayOutScoreboardTeam_nameTagVisibility;
	public static Field PacketPlayOutScoreboardTeam_collisionRule;
	public static Field PacketPlayOutScoreboardTeam_color;
	public static Field PacketPlayOutScoreboardTeam_entries;
	public static Field PacketPlayOutScoreboardTeam_action;
	public static Field PacketPlayOutScoreboardTeam_friendlyFlags;
	
	public static Class<?> PacketPlayOutScoreboardScore;
	public static Constructor<?> PacketPlayOutScoreboardScore_new;
	public static Field PacketPlayOutScoreboardScore_entry;
	public static Field PacketPlayOutScoreboardScore_objective;
	public static Field PacketPlayOutScoreboardScore_score;
	public static Field PacketPlayOutScoreboardScore_action;
	
	public static Class<?> EnumScoreboardAction;
	public static Field EnumScoreboardAction_CHANGE;
	public static Field EnumScoreboardAction_REMOVE;
	
	public static Class<?> MinecraftServer;
	public static Method MinecraftServer_getServer;
	public static Method MinecraftServer_getSessionService;
	public static Method MinecraftServer_getUserCache;
	public static Method MinecraftServer_getEntityByUID;
	public static Method MinecraftServer_getPlayerList;
	
	public static Class<?> UserCache;
	public static Method UserCache_getProfileById;
	public static Method UserCache_putProfile;
	
	public static Class<?> CraftWorld;
	public static Method CraftWorld_getHandle;
	
	public static Class<?> CraftLivingEntity;
	public static Method CraftLivingEntity_getHandle;
	
	public static Class<?> EntityHumanNonAbstract;
	public static Constructor<?> EntityHumanNonAbstract_new;
	
	public static Class<?> WorldServer;
	public static Field WorldServer_entityTracker;
	public static Method WorldServer_getPlayerChunkMap;
	public static Field WorldServer_dimension;
	
	public static Class<?> EntityTracker;
	public static Field EntityTracker_trackedEntities;
	public static Method EntityTracker_untrackPlayer;
	
	public static Class<?> IntHashMap;
	public static Method IntHashMap_get;
	
	public static Class<?> EntityTrackerEntry;
	public static Method EntityTrackerEntry_clear;
	public static Method EntityTrackerEntry_updatePlayer;
	
	public static Class<?> EnumPlayerInfoAction;
	public static Field EnumPlayerInfoAction_ADD_PLAYER;
	public static Field EnumPlayerInfoAction_REMOVE_PLAYER;
	
	public static Class<?> EnumGamemode;
	public static Field EnumGamemode_SURVIVAL;
	
	public static Class<?> PacketPlayOutEntityDestroy;
	public static Field PacketPlayOutEntityDestroy_entityIds;
	
	public static Class<?> EntityAreaEffectCloud;
	public static Method EntityAreaEffectCloud_setColor;
	public static Method EntityAreaEffectCloud_setParticle;
	public static Method EntityAreaEffectCloud_setRadius;
	public static Method EntityAreaEffectCloud_setParticleParam1;
	public static Method EntityAreaEffectCloud_setParticleParam2;
	
	public static Class<?> CraftParticle;
	public static Method CraftParticle_getData;
	public static Method CraftParticle_toNMS;
	
	public static Class<?> EntityTameableAnimal;
	public static Method EntityTameableAnimal_setSitting;
	
	public static Class<?> EnumChatFormat;
	public static Field EnumChatFormat_WHITE;
	
	public static Class<?> EntityBoat;
	public static Method EntityBoat_setType;
	
	public static Class<?> EnumBoatType;
	public static Method EnumBoatType_fromString;
	
	public static Class<?> PlayerList;
	public static Method PlayerList_sendWorldInfo;
	public static Method PlayerList_updateClient;
	
	public static Class<?> CraftBlockData;
	public static Method CraftBlockData_getHandle;
	
	public static Class<?> EntityPhantom;
	public static Method EntityPhantom_setSize;
	
	public static Class<?> EntityPufferFish;
	public static Method EntityPufferFish_setPuffState;
	
	public static Class<?> EntityTropicalFish;
	public static Method EntityTropicalFish_setVariant;
	
	public static Class<?> PlayerConnection;
	public static Field PlayerConnection_networkManager;
	
	public static Class<?> NetworkManager;
	public static Field NetworkManager_channel;
	
	public static Class<?> PacketPlayOutRespawn;
	public static Constructor<?> PacketPlayOutRespawn_new;
	
	public static Class<?> PlayerInteractManager;
	public static Method PlayerInteractManager_getGameMode;
	
	public static Class<?> PacketPlayOutPosition;
	public static Constructor<?> PacketPlayOutPosition_new;
	
	public static Class<?> PacketPlayOutSpawnPosition;
	public static Constructor<?> PacketPlayOutSpawnPosition_new;
	
	public static Class<?> PacketPlayOutExperience;
	public static Constructor<?> PacketPlayOutExperience_new;
	
	public static Class<?> PlayerChunkMap;
	public static Method PlayerChunkMap_removePlayer;
	public static Method PlayerChunkMap_addPlayer;
	
	public static Class<?> PacketPlayOutEntityEffect;
	public static Constructor<?> PacketPlayOutEntityEffect_new;
	
	public static Class<?> EntityLiving;
	public static Method EntityLiving_getEffects;
	
	public static Class<?> DataWatcher;
	public static Method DataWatcher_get;
	public static Method DataWatcher_getString;
	
	public static Class<?> DimensionManager;
	public static Method DimensionManager_fromDimensionID;
	
	public static Class<?> EntityEnderDragon;
	
	public static Class<?> PacketPlayOutEntityVelocity;
	public static Field PacketPlayOutEntityVelocity_entityId;
	
	private static final Pattern basicPattern = Pattern.compile("([A-Za-z0-9_]+)->(C|F|M|N)(.+)");
	private static final Pattern fieldPattern = Pattern.compile("([A-Za-z0-9_\\.{}]+)\\$(.+)");
	private static final Pattern methodPattern = Pattern.compile("([A-Za-z0-9_\\.{}]+)\\$([^\\(\\)]+)\\(([^\\(\\)]*)\\)");
	private static final Pattern newPattern = Pattern.compile("([A-Za-z0-9_\\.{}]+)\\(([^\\(\\)]*)\\)");
	
	public static void load(String file, String nms, String obc) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(Reflection.class.getResourceAsStream(file)));
			String line;
			while((line = reader.readLine()) != null) {
				Matcher basicMatcher = basicPattern.matcher(line);
				if(basicMatcher.matches()) {
					try {
						Field field = Reflection.class.getDeclaredField(basicMatcher.group(1));
						char type = basicMatcher.group(2).charAt(0);
						String argument = basicMatcher.group(3);
						if(type == 'C') {
							Class<?> clazz = parseClass(argument, nms, obc);
							field.set(null, clazz);
						} else if(type == 'F') {
							Matcher fieldMatcher = fieldPattern.matcher(argument);
							if(fieldMatcher.matches()) {
								Class<?> clazz = parseClass(fieldMatcher.group(1), nms, obc);
								String name = fieldMatcher.group(2);
								field.set(null, clazz.getDeclaredField(name));
								((AccessibleObject)field.get(null)).setAccessible(true);
							}
						} else if(type == 'M') {
							Matcher methodMatcher = methodPattern.matcher(argument);
							if(methodMatcher.matches()) {
								Class<?> clazz = parseClass(methodMatcher.group(1), nms, obc);
								String name = methodMatcher.group(2);
								String[] parameters = methodMatcher.group(3).length() > 0 ? methodMatcher.group(3).split(",") : new String[0];
								Class<?>[] parameterTypes = new Class<?>[parameters.length];
								for(int i = 0; i < parameters.length; i++) {
									parameterTypes[i] = parseClass(parameters[i], nms, obc);
								}
								field.set(null, clazz.getDeclaredMethod(name, parameterTypes));
								((AccessibleObject)field.get(null)).setAccessible(true);
							}
						} else if(type == 'N') {
							Matcher newMatcher = newPattern.matcher(argument);
							if(newMatcher.matches()) {
								Class<?> clazz = parseClass(newMatcher.group(1), nms, obc);
								String[] parameters = newMatcher.group(2).length() > 0 ? newMatcher.group(2).split(",") : new String[0];
								Class<?>[] parameterTypes = new Class<?>[parameters.length];
								for(int i = 0; i < parameters.length; i++) {
									parameterTypes[i] = parseClass(parameters[i], nms, obc);
								}
								field.set(null, clazz.getConstructor(parameterTypes));
								((AccessibleObject)field.get(null)).setAccessible(true);
							}
						} else {
							iDisguise.getInstance().getLogger().log(Level.SEVERE, "Cannot parse line: " + line);
						}
					} catch(Exception e) {
						iDisguise.getInstance().getLogger().log(Level.SEVERE, "Cannot parse line: " + line, e);
					}
				}
			}
		} catch(IOException e) {
			iDisguise.getInstance().getLogger().log(Level.SEVERE, "Cannot load the required reflection configuration.", e);
		}
	}
	
	public static Class<?> parseClass(String clazz, String nms, String obc) {
		switch(clazz) {
			case "boolean":
				return boolean.class;
			case "boolean[]":
				return boolean[].class;
			case "byte":
				return byte.class;
			case "byte[]":
				return byte[].class;
			case "short":
				return short.class;
			case "short[]":
				return short[].class;
			case "int":
				return int.class;
			case "int[]":
				return int[].class;
			case "long":
				return long.class;
			case "long[]":
				return long[].class;
			case "float":
				return float.class;
			case "float[]":
				return float[].class;
			case "double":
				return double.class;
			case "double[]":
				return double[].class;
		}
		if(clazz.endsWith("[]")) {
			clazz = "[L" + clazz.substring(0, clazz.length() - 2) + ";";
		}
		try {
			return Class.forName(clazz.replace("{nms}", nms).replace("{obc}", obc));
		} catch(ClassNotFoundException e) {
			try {
				Field field = Reflection.class.getDeclaredField(clazz);
				if(field.getType().equals(Class.class)) {
					return (Class<?>)field.get(null);
				}
			} catch(NoSuchFieldException | IllegalArgumentException | IllegalAccessException e2) {
			}
			if(VersionHelper.debug()) {
				iDisguise.getInstance().getLogger().log(Level.INFO, "Cannot find the given class file.", e);
			}
		}
		return null;
	}
	
}