package me.spf.teamworks.listener;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.event.WinningTeamChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class ContestListener implements Listener {

    private final Teamworks plugin;
    public ContestListener(Teamworks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWinningTeamChangeEvent(WinningTeamChangeEvent event) {
        plugin.getContestManager().getWinningTeamBar().update(event.getNewWinningTeam());
    }


}
