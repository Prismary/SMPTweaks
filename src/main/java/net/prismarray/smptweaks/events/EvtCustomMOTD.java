package net.prismarray.smptweaks.events;

import net.prismarray.smptweaks.MainConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

public class EvtCustomMOTD extends EventBase {

    @EventHandler
    public void setMOTD(ServerListPingEvent event) {
        event.setMotd(String.format(
                "%s\n%s%s",
                MainConfig.getMOTD()[0],
                MainConfig.getMOTD()[1],
                MainConfig.getMOTD()[2]
        ));
    }
}
