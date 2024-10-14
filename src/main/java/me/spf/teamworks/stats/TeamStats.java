package me.spf.teamworks.stats;

import me.spf.teamworks.team.Team;
import me.spf.teamworks.team.TeamName;
import org.bukkit.entity.Player;

import java.util.List;

public final class TeamStats {

    private int money;
    private int totalMembers;

    private int teamScore;

    public TeamStats() {
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
}
