package net.prismarray.smptweaks.recipes;

import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class RcpMending extends RecipeBase {

    public RcpMending(SMPTweaks plugin) {
        super(plugin);
    }

    @Override
    public ItemStack getItem() {
        ItemStack mendingBook = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) mendingBook.getItemMeta();
        meta.addStoredEnchant(Enchantment.MENDING, 1, true);
        mendingBook.setItemMeta(meta);

        return mendingBook;
    }

    @Override
    public Recipe getRecipe() {
        NamespacedKey key = new NamespacedKey(getPlugin(), "mending");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(
                "DDD",
                "ABA",
                "CCC"
        );

        recipe.setIngredient('A', Material.EXPERIENCE_BOTTLE);
        recipe.setIngredient('B', Material.BOOK);
        recipe.setIngredient('C', Material.NETHERITE_INGOT);
        recipe.setIngredient('D', Material.TURTLE_SCUTE);

        return recipe;
    }
}
