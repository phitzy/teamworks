package me.spf.teamworks.gui;

import me.spf.teamworks.constants.MapConstants;
import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.Teamworks;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

public class TeamGUI {
    private final Teamworks plugin;
    private final Inventory gui;

    public TeamGUI(Teamworks plugin) {
        this.plugin = plugin;
        this.gui = Bukkit.createInventory(null, 27,
                Component.text("Team Stats")
                        .color(NamedTextColor.AQUA).toString());

        for (TeamName team : plugin.getTeamManager().getTeams()) {
            ItemStack teamItem = new ItemStack(MapConstants.teamNameToBlockIcon.get(team.toString()));
            ItemMeta meta = teamItem.getItemMeta();
            Component displayName =
                    Component.text(team.toString()).color(MapConstants.teamNameToColor.get(team.toString()));
            meta.setDisplayName(displayName.toString());

            int teamScore = plugin.getContestManager().getScore(team);
            List<String> lore = List.of(
                    Component.text("Score: " + teamScore)
                            .color(NamedTextColor.WHITE).toString());

            meta.setLore(lore.stream().map(Object::toString)
                    .collect(Collectors.toList()));

            teamItem.setItemMeta(meta);
            gui.addItem(teamItem);
        }
    }

    public void openTeamGUI(Player player) {
        plugin.getLogger().info("Opening teams gui inventory for player: " + player.getName());
        player.openInventory(gui);
    }
}
