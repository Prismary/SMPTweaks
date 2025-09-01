package net.prismarray.smptweaks.events;

import net.prismarray.smptweaks.MainConfig;
import net.prismarray.smptweaks.Tab;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class EvtSendTab extends EventBase {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        if (MainConfig.isTABEnabled()) {
            Tab.setPlayerTab(event.getPlayer(), MainConfig.getTAB()[0], MainConfig.getTAB()[1]);
        }
    }

}
