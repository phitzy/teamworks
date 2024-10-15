package me.spf.teamworks.team;

import me.spf.teamworks.constants.MapConstants;
import me.spf.teamworks.gui.WinningTeamBar;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;

public class GenerateBossBar {

    public static BossBar forTeam(TeamName teamName) {
        Component name = Component.text(teamName.name())
                .color(MapConstants.getTeamNameToColor().get(teamName.toString()));
        return BossBar.bossBar(
                name, 1, MapConstants.getTeamNameToBossBarColor().get(teamName), BossBar.Overlay.NOTCHED_10
        );
    }
}
