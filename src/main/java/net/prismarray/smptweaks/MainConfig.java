package net.prismarray.smptweaks;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;

public class MainConfig extends Config {

    private static MainConfig instance = new MainConfig("config.yml");

    private MainConfig(String fileName) {
        super(fileName);
    }

    public static MainConfig getInstance() {
        return instance;
    }


    // Specific Methods

    // Booleans
    public static boolean isLocked() {
        return getInstance().getConfig().getBoolean("locked");
    }

    public static boolean isMOTDEnabled() {
        return getInstance().getConfig().getBoolean("motd.enable");
    }

    public static boolean isMOTDRandom() {
        return getInstance().getConfig().getBoolean("motd.randomize");
    }

    public static boolean isTABEnabled() {
        return getInstance().getConfig().getBoolean("tab.enable");
    }

    public static boolean isDiscordEnabled() {
        return getInstance().getConfig().getBoolean("discord.enable");
    }

    public static boolean useInviteCooldown() {
        return getInstance().getConfig().getBoolean("inviteCooldown");
    }

    public static boolean isSpawnEnabled() {
        return getInstance().getConfig().getBoolean("spawn.enable");
    }

    public static boolean noMendingTrades() {
        return getInstance().getConfig().getBoolean("balancing.noMendingTrades");
    }

    public static boolean noElytraSpawns() {
        return getInstance().getConfig().getBoolean("balancing.noElytraSpawns");
    }

    public static boolean noShulkersInEnderchest() {
        return getInstance().getConfig().getBoolean("balancing.noShulkersInEnderchest");
    }

    public static boolean noElytraEnchants() {
        return getInstance().getConfig().getBoolean("balancing.noElytraEnchants");
    }

    public static boolean noElytraRepair() {
        return getInstance().getConfig().getBoolean("balancing.noElytraRepair");
    }

    public static boolean isCreeperDamageDisabled() {
        return getInstance().getConfig().getBoolean("disableCreeperBlockDamage");
    }

    // Crafting
    public static boolean isTotemCraftingEnabled() {
        return getInstance().getConfig().getBoolean("crafting.totemOfPreservation");
    }

    public static boolean isElytraCraftingEnabled() {
        return getInstance().getConfig().getBoolean("crafting.elytra");
    }

    // Other getters
    public static String[] getStaticMOTD() {
        return new String[]{
                getInstance().getConfig().getString("motd.line1"),
                getInstance().getConfig().getString("motd.line2"),
                getInstance().getConfig().getString("motd.message"),
                getInstance().getConfig().getString("motd.maintenance")
        };
    }

    public static List<String> getMOTDPool() {
        return getInstance().getConfig().getStringList("motd.pool");
    }

    public static String[] getTAB() {
        return new String[]{
                getInstance().getConfig().getString("tab.header"),
                getInstance().getConfig().getString("tab.footer")
        };
    }

    public static String[] getDiscord() {
        return new String[]{
                getInstance().getConfig().getString("discord.message"),
                getInstance().getConfig().getString("discord.link")
        };
    }

    public static Location getSpawn() {
        return new Location(
                Bukkit.getWorlds().get(0),
                (double) getInstance().getConfig().getInt("spawn.location.X"),
                (double) getInstance().getConfig().getInt("spawn.location.Y"),
                (double) getInstance().getConfig().getInt("spawn.location.Z")
        );
    }

    // Setters

    public static void setLock(boolean newLockState) {
        getInstance().getConfig().set("locked", newLockState);
        getInstance().save();
    }

    public static void setStaticMessage(String appendix) {
        getInstance().getConfig().set("motd.message", appendix);
        getInstance().save();
    }
}
