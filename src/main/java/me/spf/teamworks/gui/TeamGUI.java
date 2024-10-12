package me.spf.teamworks.gui;

import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.Teamworks;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeamGUI {
    private final Teamworks plugin;

    private final Map<String, Material> teamNameToBlockIcon;
    private final Map<String, NamedTextColor> teamNameToColor;
    private final Map<TeamName, BossBar.Color> teamNameToBossBarColor;

    public TeamGUI(Teamworks plugin) {
        this.plugin = plugin;
        this.teamNameToBlockIcon = new HashMap<>();
        this.teamNameToColor = new HashMap<>();
        this.teamNameToBossBarColor = new HashMap<>();
        teamNameToBlockIcon.put("RED", Material.RED_STAINED_GLASS_PANE);
        teamNameToBlockIcon.put("BLUE", Material.BLUE_STAINED_GLASS_PANE);
        teamNameToBlockIcon.put("GREEN", Material.GREEN_STAINED_GLASS_PANE);
        teamNameToBlockIcon.put("YELLOW", Material.YELLOW_STAINED_GLASS_PANE);

        teamNameToColor.put("RED", NamedTextColor.RED);
        teamNameToColor.put("BLUE", NamedTextColor.BLUE);
        teamNameToColor.put("GREEN", NamedTextColor.GREEN);
        teamNameToColor.put("YELLOW", NamedTextColor.YELLOW);

        teamNameToBossBarColor.put(TeamName.RED, BossBar.Color.RED);
        teamNameToBossBarColor.put(TeamName.BLUE, BossBar.Color.BLUE);
        teamNameToBossBarColor.put(TeamName.GREEN, BossBar.Color.GREEN);
        teamNameToBossBarColor.put(TeamName.YELLOW, BossBar.Color.YELLOW);
    }

    public void openTeamGUI(Player player) {
        Inventory gui =
                Bukkit.createInventory(null, 27,
                        Component.text("Team Stats")
                                .color(NamedTextColor.AQUA).toString());

        plugin.getLogger().info("Looping through teams");
        for (TeamName team : plugin.getTeamManager().getTeams()) {
            ItemStack teamItem = new ItemStack(teamNameToBlockIcon.get(team.toString()));
            ItemMeta meta = teamItem.getItemMeta();
            Component displayName =
                    Component.text(team.toString()).color(teamNameToColor.get(team.toString()));
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
        plugin.getLogger().info("Opening teams gui inventory");
        player.openInventory(gui);
    }

    public Map<String, NamedTextColor> getTeamNameToColor() {
        return teamNameToColor;
    }

    public Map<String, Material> getTeamNameToBlockIcon() {
        return teamNameToBlockIcon;
    }

    public Map<TeamName, BossBar.Color> getTeamNameToBossBarColor() {
        return teamNameToBossBarColor;
    }
}
