package me.spf.teamworks.stats;

import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.invasion.InvadingGroup;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerStats {

    private final Player player;
    private final UUID uuid;
    private TeamName team;
    private int score;
    private int numberOfInvasions;
    private int kills;
    private int deaths;
    private float xp;
    private int money;

    private InvadingGroup group;

    public PlayerStats(Player player) {
        this.player = player;
        score = 0;
        numberOfInvasions = 0;
        kills = 0;
        deaths = 0;
        xp = 0;
        money = 0;
        uuid = player.getUniqueId();
    }

    public TeamName getTeam() {
        return team;
    }

    public void setTeam(TeamName team) {
        this.team = team;
    }

    public boolean setTeam(String team) {
        try {
            this.team = TeamName.valueOf(team);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberOfInvasions() {
        return numberOfInvasions;
    }

    public void setNumberOfInvasions(int numberOfInvasions) {
        this.numberOfInvasions = numberOfInvasions;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public float getXp() {
        return xp;
    }

    public InvadingGroup getGroup() {
        return group;
    }

    public void setGroup(InvadingGroup group) {
        this.group = group;
    }

    public boolean inGroup() {
        return group != null && !group.getPlayers().isEmpty();
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
