package net.prismarray.smptweaks.events;

import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public class EvtPreventElytraRepair extends EventBase {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();
        AnvilInventory inv = event.getInventory();

        if (result != null && result.getType() == Material.ELYTRA) {
            ItemStack second = inv.getItem(1);

            if ((second != null && second.getType() == Material.PHANTOM_MEMBRANE)) {
                event.setResult(null);
                //SMPTweaks.getInstance().getLogger().info("Prevented elytra repair in anvil!");
            }
        }
    }

}
