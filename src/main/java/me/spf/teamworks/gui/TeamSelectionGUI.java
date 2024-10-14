package me.spf.teamworks.gui;

import me.spf.teamworks.team.Team;
import me.spf.teamworks.team.TeamName;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamSelectionGUI {

    private static final String GUI_TITLE = "Team Selection";
    private static final int GUI_SIZE = 27;

    public void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, GUI_SIZE, GUI_TITLE);
        int slot = 10;

    }

    private void addTeamItem(Inventory gui, int slot,
                             Team team, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(team.getName().toString());
        item.setItemMeta(meta);
        gui.setItem(slot, item);
    }

}
