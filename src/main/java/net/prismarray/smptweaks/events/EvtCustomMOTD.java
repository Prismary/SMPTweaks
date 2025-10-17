package net.prismarray.smptweaks.events;

import net.prismarray.smptweaks.MainConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.List;
import java.util.Random;

public class EvtCustomMOTD extends EventBase {

    @EventHandler
    public void setMOTD(ServerListPingEvent event) {
        if (MainConfig.isLocked()) {
            setMaintenanceMOTD(event);
            return;
        }

        event.setMotd(String.format(
                "%s\n%s%s",
                MainConfig.getStaticMOTD()[0],
                MainConfig.getStaticMOTD()[1],
                MainConfig.isMOTDRandom() ? getRandomMOTD() : MainConfig.getStaticMOTD()[2]
        ));

    }

    public String getRandomMOTD() {
        List<String> motds = MainConfig.getMOTDPool();
        if (motds.isEmpty()) {
            return "";
        }

        Random random = new Random();
        return motds.get(random.nextInt(motds.size()));
    }

    public void setMaintenanceMOTD(ServerListPingEvent event) {
        event.setMotd(String.format(
                "%s\n%s",
                MainConfig.getStaticMOTD()[0],
                MainConfig.getStaticMOTD()[3]
        ));

        event.setMaxPlayers(0);
    }
}
