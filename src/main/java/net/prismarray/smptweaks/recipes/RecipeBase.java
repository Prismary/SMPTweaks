package net.prismarray.smptweaks.recipes;

import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public abstract class RecipeBase {
    private SMPTweaks plugin;

    public RecipeBase(SMPTweaks plugin) {
        this.plugin = plugin;

        register();
    }

    public SMPTweaks getPlugin() {
        return plugin;
    }


    private void register() {
        Bukkit.addRecipe(getRecipe());
    }

    public abstract ItemStack getItem();
    public abstract Recipe getRecipe();
}
