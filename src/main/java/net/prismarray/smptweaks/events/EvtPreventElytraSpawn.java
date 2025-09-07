package net.prismarray.smptweaks.events;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.ChunkLoadEvent;

public class EvtPreventElytraSpawn extends EventBase {

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (!event.isNewChunk()) return; // only check new chunks

        Chunk chunk = event.getChunk();
        for (Entity entity : chunk.getEntities()) {
            if (entity instanceof ItemFrame frame) {
                if (frame.getItem().getType() == Material.ELYTRA) {
                    frame.setItem(null);
                    //SMPTweaks.getInstance().getLogger().info("Elytra removed on new chunk load!");
                }
            }
        }
    }

}
