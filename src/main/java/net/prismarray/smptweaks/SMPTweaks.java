package net.prismarray.smptweaks;

import net.prismarray.smptweaks.commands.*;
import net.prismarray.smptweaks.events.*;
import net.prismarray.smptweaks.recipes.RcpElytra;
import net.prismarray.smptweaks.recipes.RcpTotemOfPreservation;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMPTweaks extends JavaPlugin {

    private static SMPTweaks instance;

    @Override
    public void onEnable() {
        instance = this;

        registerCommands();
        registerEvents();
        registerRecipes();
    }

    @Override
    public void onDisable() {
        MainConfig.getInstance().save();
        InviteLog.getInstance().save();
    }

    public static SMPTweaks getInstance() {
        return instance;
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new EvtInventoryPreservation(), this);
        getServer().getPluginManager().registerEvents(new EvtPreventUnintendedCraft(), this);
        getServer().getPluginManager().registerEvents(new EvtPlayerLogin(), this);
        getServer().getPluginManager().registerEvents(new EvtSendTab(), this);

        if (MainConfig.isMOTDEnabled()) {
            getServer().getPluginManager().registerEvents(new EvtCustomMOTD(), this);
        }

        if (MainConfig.isCreeperDamageDisabled()) {
            getServer().getPluginManager().registerEvents(new EvtCreeperExplosion(), this);
        }

        if (MainConfig.isSpawnEnabled()) {
            getServer().getPluginManager().registerEvents(new EvtSetSpawnLocation(), this);
        }

        if (MainConfig.noMendingTrades()) {
            getServer().getPluginManager().registerEvents(new EvtMendingTrade(), this);
        }

        if (MainConfig.noElytraSpawns()) {
            getServer().getPluginManager().registerEvents(new EvtPreventElytraSpawn(), this);
        }

        if (MainConfig.noShulkersInEnderchest()) {
            getServer().getPluginManager().registerEvents(new EvtNoShulkerboxInEnderchest(), this);
        }

        if (MainConfig.noElytraEnchants()) {
            getServer().getPluginManager().registerEvents(new EvtPreventElytraEnchants(), this);
        }

        if (MainConfig.noElytraRepair()) {
            getServer().getPluginManager().registerEvents(new EvtPreventElytraRepair(), this);
        }
    }

    private void registerCommands() {
        getCommand("invite").setExecutor(new CmdInvite());
        getCommand("suspend").setExecutor(new CmdSuspend());
        getCommand("reinstate").setExecutor(new CmdReinstate());
        getCommand("purge").setExecutor(new CmdPurge());
        getCommand("lock").setExecutor(new CmdLock());
        getCommand("unlock").setExecutor(new CmdUnlock());
        getCommand("reload").setExecutor(new CmdReload());
        getCommand("motd").setExecutor(new CmdMotd());
        getCommand("playerinfo").setExecutor(new CmdPlayerInfo());
        getCommand("trace").setExecutor(new CmdTrace());

        if (MainConfig.isDiscordEnabled()) {
            getCommand("discord").setExecutor(new CmdDiscord());
        }
    }

    private void registerRecipes() {
        if (MainConfig.isTotemCraftingEnabled()) {
            new RcpTotemOfPreservation(this);
        }
        if (MainConfig.isElytraCraftingEnabled()) {
            new RcpElytra(this);
        }
    }
}

