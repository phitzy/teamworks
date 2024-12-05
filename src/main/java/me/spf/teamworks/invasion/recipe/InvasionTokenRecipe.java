package me.spf.teamworks.invasion.recipe;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.managers.InvasionManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

/**
 * Recipe shaping is pretty identical to how it works in the game
 *
 */
public class InvasionTokenRecipe implements Recipe {

    private final ShapedRecipe recipe;
    private final NamespacedKey key;
    private final ItemStack resultItem;

    /**
     * TODO: On use of item, send request for invasion command
     * @param plugin
     */
    public InvasionTokenRecipe(Teamworks plugin) {
        key = new NamespacedKey(plugin, "invasion_token");
        resultItem = new ItemStack(Material.EMERALD, 1);
        ItemMeta meta = resultItem.getItemMeta();
        meta.displayName(Component.text(InvasionManager.INVASION_TOKEN));
        recipe = new ShapedRecipe(key, resultItem);
        shape(recipe);
        Bukkit.addRecipe(recipe);
    }


    /**
     * @see InvasionTokenRecipe
     * @param recipe
     */
    private void shape(@NotNull ShapedRecipe recipe) {
        recipe.shape(" D ", " G ", " E "); // center 3 down
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('E', Material.ENDER_PEARL);
    }

    @Override
    public @NotNull ItemStack getResult() {
        return resultItem;
    }
}
