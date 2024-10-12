package me.spf.teamworks.managers;

import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.Teamworks;

import java.util.HashMap;
import java.util.Map;

public class ContestManager {

    private final Teamworks plugin;
    private final Map<TeamName, Integer> teamScores = new HashMap<>();

    public ContestManager(Teamworks plugin) {
        this.plugin = plugin;
        // load scores from file or db
        teamScores.put(TeamName.RED, 0);
        teamScores.put(TeamName.BLUE, 0);
        teamScores.put(TeamName.GREEN, 0);
        teamScores.put(TeamName.YELLOW, 0);
    }

    public int getScore(TeamName team) {
        return teamScores.get(team);
    }

    public void updateScore(TeamName team, int amount) {
        if (teamScores.containsKey(team)) {
            teamScores.put(team, teamScores.get(team) + amount);
        }
    }

    public TeamName getWinningTeam() {
        return teamScores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(TeamName.NONE);
    }
}
