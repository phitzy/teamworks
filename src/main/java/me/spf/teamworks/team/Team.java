package me.spf.teamworks.team;

import me.spf.teamworks.stats.TeamStats;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final TeamName name;
    private String description;
    private List<? extends Player> players;
    private TeamStats teamStats;

    public Team(TeamName name, String description, List<? extends Player> players) {
        this.name = name;
        this.description = description;
        this.players = players;
        this.teamStats = new TeamStats();
    }

    public Team(TeamName name, String description) {
        this.name = name;
        this.description = description;
        this.players = new ArrayList<>();
        this.teamStats = new TeamStats();
    }

    public Team(TeamName name) {
        this.name = name;
        this.players = new ArrayList<>();
        this.description = "";
        this.teamStats = new TeamStats();
    }

    public TeamName getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<? extends Player> getPlayers() {
        return players;
    }
}
