package net.prismarray.smptweaks.events;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class EvtMendingTrade extends EventBase {

    @EventHandler
    public void onVillagerAcquireTrade(VillagerAcquireTradeEvent event) {
        ItemStack result = event.getRecipe().getResult();

        if (result.getType() == org.bukkit.Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) result.getItemMeta();
            if (meta != null && meta.hasStoredEnchant(Enchantment.MENDING)) {
                event.setCancelled(true);
                //SMPTweaks.getInstance().getLogger().info("Mending trade removed on acquiring trade!");
            }
        }
    }
}
