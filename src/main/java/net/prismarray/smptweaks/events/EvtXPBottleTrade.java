package net.prismarray.smptweaks.events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.ArrayList;
import java.util.List;

public class EvtXPBottleTrade extends EventBase {

    @EventHandler
    public void onVillagerAcquireTrade(VillagerAcquireTradeEvent event) {
        ItemStack result = event.getRecipe().getResult();

        if (result.getType() == Material.EXPERIENCE_BOTTLE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onVillagerInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager villager) {
            List<MerchantRecipe> recipes = new ArrayList<>(villager.getRecipes());
            recipes.removeIf(recipe -> recipe.getResult().getType() == Material.EXPERIENCE_BOTTLE);
            villager.setRecipes(recipes);
        }
    }
}
