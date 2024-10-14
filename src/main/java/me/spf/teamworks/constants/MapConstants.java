package me.spf.teamworks.constants;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.team.TeamName;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public final class MapConstants {
    public static final Map<String, Material> teamNameToBlockIcon = new HashMap<>();
    public static final Map<String, NamedTextColor> teamNameToColor = new HashMap<>();
    public static final Map<TeamName, BossBar.Color> teamNameToBossBarColor = new HashMap<>();

    static {
        init();
    }

    private static void init() {

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

    public static Map<String, Material> getTeamNameToBlockIcon() {
        return teamNameToBlockIcon;
    }

    public static Map<String, NamedTextColor> getTeamNameToColor() {
        return teamNameToColor;
    }

    public static Map<TeamName, BossBar.Color> getTeamNameToBossBarColor() {
        return teamNameToBossBarColor;
    }

}
