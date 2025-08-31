package net.prismarray.smptweaks.events;

import net.prismarray.smptweaks.MainConfig;
import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class EvtSetSpawnLocation extends EventBase {

    @EventHandler
    public void playerRespawn(PlayerRespawnEvent event) {
        if (event.isBedSpawn() || event.isAnchorSpawn()) {
            return;
        }

        event.setRespawnLocation(MainConfig.getSpawn());
    }

    @EventHandler
    public void firstLogin(PlayerLoginEvent event) {
        if (!Bukkit.getOfflinePlayer(event.getPlayer().getUniqueId()).hasPlayedBefore()) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(SMPTweaks.getInstance(), new Runnable() {
                @Override
                public void run() {
                    event.getPlayer().teleport(MainConfig.getSpawn());
                }
            }, 1);
        }
    }
}
