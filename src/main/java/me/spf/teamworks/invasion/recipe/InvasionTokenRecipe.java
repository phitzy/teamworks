package me.spf.teamworks.invasion.recipe;

import me.spf.teamworks.Teamworks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

public class InvasionTokenRecipe implements Recipe {

    private final ShapedRecipe recipe;
    private final NamespacedKey key;
    private final ItemStack resultItem;

    public InvasionTokenRecipe(Teamworks plugin) {
        key = new NamespacedKey(plugin, "invasion_token");
        resultItem = new ItemStack(Material.EMERALD, 1);
        recipe = new ShapedRecipe(key, resultItem);
        shape(recipe);
        Bukkit.addRecipe(recipe);
    }

    private void shape(@NotNull ShapedRecipe recipe) {
        recipe.shape(" D ", " G ", " E ");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('E', Material.ENDER_PEARL);
    }

    @Override
    public @NotNull ItemStack getResult() {
        return resultItem;
    }
}
