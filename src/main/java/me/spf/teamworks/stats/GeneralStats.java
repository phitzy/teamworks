package me.spf.teamworks.stats;

import me.spf.teamworks.Teamworks;

public final class GeneralStats {

    private long blocksBrokenByPlayers;
    private long totalMoneyInCirculation;
    private long totalPlayerDeaths;
    private long totalPlayerKills;
    private long totalEntityDeaths;

    public GeneralStats() {

    }



    public long getBlocksBrokenByPlayers() {
        return blocksBrokenByPlayers;
    }

    public void setBlocksBrokenByPlayers(long blocksBrokenByPlayers) {
        this.blocksBrokenByPlayers = blocksBrokenByPlayers;
    }

    public long getTotalMoneyInCirculation() {
        return totalMoneyInCirculation;
    }

    public void setTotalMoneyInCirculation(long totalMoneyInCirculation) {
        this.totalMoneyInCirculation = totalMoneyInCirculation;
    }

    public long getTotalPlayerDeaths() {
        return totalPlayerDeaths;
    }

    public void setTotalPlayerDeaths(long totalPlayerDeaths) {
        this.totalPlayerDeaths = totalPlayerDeaths;
    }

    public long getTotalPlayerKills() {
        return totalPlayerKills;
    }

    public void setTotalPlayerKills(long totalPlayerKills) {
        this.totalPlayerKills = totalPlayerKills;
    }

    public long getTotalEntityDeaths() {
        return totalEntityDeaths;
    }

    public void setTotalEntityDeaths(long totalEntityDeaths) {
        this.totalEntityDeaths = totalEntityDeaths;
    }
}
