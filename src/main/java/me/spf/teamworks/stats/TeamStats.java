package me.spf.teamworks.stats;

import me.spf.teamworks.team.TeamName;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class TeamStats {

    private int money;
    private int totalMembers;
    private List<Player> playerList;
    private TeamName teamName;

    public TeamStats(String name) {

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public TeamName getTeamName() {
        return teamName;
    }
}
