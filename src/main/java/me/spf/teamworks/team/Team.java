package me.spf.teamworks.team;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private TeamName name;
    private String description;
    private List<? extends Player> players;

    public Team(TeamName name, String description, List<? extends Player> players) {
        this.name = name;
        this.description = description;
        this.players = players;
    }

    public Team(TeamName name, String description) {
        this.name = name;
        this.description = description;
        this.players = new ArrayList<>();
    }

    public Team(TeamName name) {
        this.name = name;
        this.players = new ArrayList<>();
        this.description = "";
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
