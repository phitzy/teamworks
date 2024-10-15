package me.spf.teamworks.managers;

import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.Teamworks;
import me.spf.teamworks.stats.PlayerStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TeamManager {

    private final Teamworks plugin;

    private final Map<Player, TeamName> playerTeams = new HashMap<>();
    private final Map<Player, PlayerStats> playerStats = new HashMap<>();
    private final List<TeamName> teams = List.of(TeamName.values());

    private int numberOfPlayersOnRed;
    private int numberOfPlayersOnBlue;
    private int numberOfPlayersOnGreen;
    private int numberOfPlayersOnYellow;

    public TeamManager(Teamworks plugin) {
        this.plugin = plugin;
    }

    public void assignTeam(Player player) {
        TeamName teamName = TeamName.values()[new Random().nextInt(TeamName.values().length)];
        while (teamName.equals(TeamName.NONE)) {
            teamName = TeamName.values()[new Random().nextInt(TeamName.values().length)];
        }
        assignTeam(player, teamName);
        switch (teamName) {
            case RED -> numberOfPlayersOnRed++;
            case BLUE -> numberOfPlayersOnBlue++;
            case GREEN -> numberOfPlayersOnGreen++;
            case YELLOW -> numberOfPlayersOnYellow++;
        }
    }

    public void assignTeam(Player player, TeamName teamName) {
        PlayerStats stats = plugin.getTeamManager().getPlayerStats(player);
        if (stats == null) {
            stats = new PlayerStats(player);
            stats.setTeam(teamName);
            playerStats.put(player, stats);
            playerTeams.put(player, teamName);
            return;
        }
        stats.setTeam(teamName);
        playerStats.replace(player, stats);
        playerTeams.replace(player, teamName);
    }

    public void removeFromTeam(Player player) {
        if (!player.hasPlayedBefore()) return;
        TeamName teamName = playerTeams.get(player);
        if (teamName == null) return;
        PlayerStats stats = playerStats.get(player);
        if (stats == null) return;
        stats.setTeam(TeamName.NONE);
        playerStats.replace(player, stats);
        playerTeams.replace(player, TeamName.NONE);

        switch (stats.getTeam()) {
            case RED -> numberOfPlayersOnRed--;
            case BLUE -> numberOfPlayersOnBlue--;
            case GREEN -> numberOfPlayersOnGreen--;
            case YELLOW -> numberOfPlayersOnYellow--;
        }
    }

    public List<TeamName> getTeams() {
        return teams;
    }

    public List<String> getTeamsAsStrings() {
        return List.of(teams.stream().map(TeamName::toString).toArray(String[]::new));
    }

    public PlayerStats getPlayerStats(Player player) {
        return playerStats.get(player);
    }

    public PlayerStats getPlayerStats(String playerName) {
        for (PlayerStats playerStats : playerStats.values()) {
            if (playerStats.getPlayer().getName().equalsIgnoreCase(playerName)) {
                return playerStats;
            }
        }
        return null;
    }

    public String getPlayerTeamAsString(Player player) {
        return playerTeams.get(player).toString();
    }

    public TeamName getPlayerTeam(Player player) {
        return playerTeams.get(player);
    }

    public int getNumberOfPlayersOn(TeamName teamName) {
        switch (teamName) {
            case RED -> {
                return numberOfPlayersOnRed;
            }
            case BLUE -> {
                return numberOfPlayersOnBlue;
            }
            case GREEN -> {
                return numberOfPlayersOnGreen;
            }
            case YELLOW -> {
                return numberOfPlayersOnYellow;
            }
        }
        return 0;
    }

}
