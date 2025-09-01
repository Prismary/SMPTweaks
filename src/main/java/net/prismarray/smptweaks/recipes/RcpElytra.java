package net.prismarray.smptweaks.recipes;

import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class RcpElytra extends RecipeBase {

    public RcpElytra(SMPTweaks plugin) {
        super(plugin);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.ELYTRA);

        return item;
    }

    @Override
    public Recipe getRecipe() {
        NamespacedKey key = new NamespacedKey(getPlugin(), "elytra");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(
                "ABA",
                "CDC",
                "EFE"
        );

        recipe.setIngredient('A', Material.WITHER_ROSE);
        recipe.setIngredient('B', Material.DRAGON_HEAD);
        recipe.setIngredient('C', Material.ECHO_SHARD);
        recipe.setIngredient('D', Material.CHAINMAIL_CHESTPLATE);
        recipe.setIngredient('E', Material.WIND_CHARGE);
        recipe.setIngredient('F', Material.ENCHANTED_GOLDEN_APPLE);

        return recipe;
    }
}
