package net.prismarray.smptweaks;

import net.prismarray.smptweaks.commands.*;
import net.prismarray.smptweaks.events.*;
import net.prismarray.smptweaks.recipes.RcpTotemOfPreservation;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMPTweaks extends JavaPlugin {

    private static SMPTweaks instance;
    private static boolean isLocked;

    @Override
    public void onEnable() {
        instance = this;
        isLocked = false;

        registerCommands();
        registerEvents();

        if (MainConfig.isCraftingEnabled()) {
            registerRecipes();
        }
    }

    @Override
    public void onDisable() {
        MainConfig.getInstance().save();
        VouchLog.getInstance().save();
    }

    public static SMPTweaks getInstance() {
        return instance;
    }

    public void toggleLock() {
        isLocked = !isLocked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new EvtInventoryPreservation(), this);
        getServer().getPluginManager().registerEvents(new EvtPreventUnintendedCraft(), this);
        getServer().getPluginManager().registerEvents(new EvtPlayerLogin(), this);

        if (MainConfig.isMOTDEnabled()) {
            getServer().getPluginManager().registerEvents(new EvtCustomMOTD(), this);
        }

        if (MainConfig.isCreeperDamageDisabled()) {
            getServer().getPluginManager().registerEvents(new EvtCreeperExplosion(), this);
        }

        if (MainConfig.isSpawnEnabled()) {
            getServer().getPluginManager().registerEvents(new EvtSetSpawnLocation(), this);
        }
    }

    private void registerCommands() {
        getCommand("vouch").setExecutor(new CmdVouch());
        getCommand("suspend").setExecutor(new CmdSuspend());
        getCommand("reinstate").setExecutor(new CmdReinstate());
        getCommand("purge").setExecutor(new CmdPurge());
        getCommand("lock").setExecutor(new CmdLock());
        getCommand("reload").setExecutor(new CmdReload());
        getCommand("motd").setExecutor(new CmdMotd());
        getCommand("playerinfo").setExecutor(new CmdPlayerInfo());

        if (MainConfig.isDiscordEnabled()) {
            getCommand("discord").setExecutor(new CmdDiscord());
        }
    }

    private void registerRecipes() {
        new RcpTotemOfPreservation(this);
    }
}

