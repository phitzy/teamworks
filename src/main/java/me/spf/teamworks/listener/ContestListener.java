package me.spf.teamworks.listener;

import me.spf.teamworks.Teamworks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ContestListener implements Listener {

    private final Teamworks plugin;

    public ContestListener(Teamworks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWinningTeamChange() {

    }

}
