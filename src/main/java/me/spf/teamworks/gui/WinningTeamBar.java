package me.spf.teamworks.gui;

import me.spf.teamworks.constants.MapConstants;
import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.Teamworks;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class WinningTeamBar {

    private BossBar bossBar;
    private final Teamworks plugin;
    private TeamName winningTeamName;

    public WinningTeamBar(Teamworks plugin) {
        this.plugin = plugin;
        bossBar = BossBar.bossBar(Component.text(""), 0f, BossBar.Color.BLUE, BossBar.Overlay.NOTCHED_10);
        winningTeamName = TeamName.valueOf(plugin.getContestManager().getWinningTeam().toString());
        update();
    }

    public void update() {
        TeamName winning = TeamName.valueOf(plugin.getContestManager().getWinningTeam().toString());
        if (winning == winningTeamName) return;
        winningTeamName = winning;
        Component component = Component.text("Winning team: " +
                winningTeamName).color(
                MapConstants.getTeamNameToColor().get(winningTeamName.toString())
        );
        BossBar.Color color = MapConstants.getTeamNameToBossBarColor().get(winningTeamName);
        setBossBar(BossBar.bossBar(component, 1f, color, BossBar.Overlay.NOTCHED_10));
    }

    public void showFor(Player player) {
        if (player == null) return;
        player.showBossBar(bossBar);
    }

    public void hideFor(Player player) {
        if (player == null) return;
        player.hideBossBar(bossBar);
    }

    public void showForAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            showFor(player);
        }
    }

    public void hideForAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            hideFor(player);
        }
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public void setBossBar(BossBar bossBar) {
        this.bossBar = bossBar;
    }
}
