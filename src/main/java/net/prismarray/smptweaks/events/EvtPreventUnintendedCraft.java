package net.prismarray.smptweaks.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class EvtPreventUnintendedCraft extends EventBase {
    @EventHandler
    public void preventCustomCraft(CraftItemEvent event) {
        ItemStack[] workbenchContent = event.getInventory().getMatrix();

        for (ItemStack itemStack : workbenchContent) {
            if (itemStack != null && itemStack.getItemMeta().getDisplayName().equals("§aTotem of Preservation")) {
                event.setCancelled(true);
            }
        }
    }
}
