package me.spf.teamworks.invasion;

import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.managers.TeamManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InvadingGroup {
    private List<Player> players;
    private final TeamManager teamManager;

    private TeamName team;

    public InvadingGroup(TeamManager teamManager, List<Player> players) {
        if (!validGroup(players)) {
            this.teamManager = teamManager;
            this.players = new ArrayList<>();
            return;
        }
        this.players = players;
        this.teamManager = teamManager;
        this.team = teamManager.getPlayerTeam(players.getFirst());
    }

    public boolean addPlayerToGroup(Player player) {

        if (teamManager.getPlayerStats(player) == null) {
            return false;
        }
        if (teamManager.getPlayerStats(player).inGroup()) {
            return false;
        }

        if (!(teamManager.getPlayerTeam(player).equals(team))) {
            return false;
        }

        players.add(player);
        teamManager.getPlayerStats(player).setGroup(this);
        return true;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Method to assert players from different teams cannot group up
     * @param players given list of players
     * @return
     */
    public boolean validGroup(List<Player> players) {
        boolean flag = true;
        for (int i = 0; i < players.size(); i++) {
            for (Player player : players) {
                assert teamManager != null;
                if (!(teamManager.getPlayerTeam(player).equals(teamManager.getPlayerTeam(players.get(i))))) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     *
     * @return team in TeamName format
     * @see TeamName
     */
    public TeamName getTeam() {
        return team;
    }
}
