package net.prismarray.smptweaks.events;

import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class EvtPreventElytraEnchants extends EventBase {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();
        if (result != null && result.getType() == Material.ELYTRA && !result.getEnchantments().isEmpty()) {
            event.setResult(null);
            //SMPTweaks.getInstance().getLogger().info("Elytra enchant prevented on anvil!");
        }
    }

}
