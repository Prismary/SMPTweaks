package net.prismarray.smptweaks.recipes;

import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class RcpTotemOfPreservation extends RecipeBase {

    public RcpTotemOfPreservation(SMPTweaks plugin) {
        super(plugin);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.ECHO_SHARD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§aTotem of Preservation");

        item.setItemMeta(meta);

        return item;
    }

    @Override
    public Recipe getRecipe() {
        NamespacedKey key = new NamespacedKey(getPlugin(), "totem_of_preservation");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(
                "CAC",
                "ADA",
                "CAC"
        );

        recipe.setIngredient('C', Material.COPPER_BLOCK);
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        recipe.setIngredient('D', Material.DIRT);

        return recipe;
    }
}
