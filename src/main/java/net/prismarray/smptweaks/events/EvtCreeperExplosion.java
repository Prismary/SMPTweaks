package net.prismarray.smptweaks.events;

import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EvtCreeperExplosion extends EventBase {

    @EventHandler
    public void creeperExplosion(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof Creeper)) {
            return;
        }

        event.blockList().clear();
    }
}
