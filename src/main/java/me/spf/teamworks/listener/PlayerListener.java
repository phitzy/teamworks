package me.spf.teamworks.listener;

import me.spf.teamworks.event.WinningTeamChangeEvent;
import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.Teamworks;
import me.spf.teamworks.stats.PlayerStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public final class PlayerListener implements Listener {
    private final Teamworks plugin;

    public PlayerListener(Teamworks plugin) {
        this.plugin = plugin;
    }

    /**
     * Method updates the scores whenever a player is killed
     * @param event the death event that triggers this method
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        TeamName team = plugin.getTeamManager().getPlayerTeam(event.getEntity());
        Player killed = event.getEntity();
        Player killer = event.getEntity().getKiller();
        TeamName oteam = plugin.getTeamManager().getPlayerTeam(killer);

        PlayerStats killedStats = plugin.getTeamManager().getPlayerStats(killed);
        PlayerStats killerStats = plugin.getTeamManager().getPlayerStats(killer);

        int firstAmt = killedStats.getDeaths();
        int secondAmt = killerStats.getKills();

        killedStats.setDeaths(firstAmt + 1);
        killerStats.setKills(secondAmt + 1);

        plugin.getContestManager().updateScore(oteam, plugin.getContestManager().getScore(team) + 1);
        plugin.getContestManager().updateScore(team, plugin.getContestManager().getScore(team) - 1);

        if (plugin.getContestManager().getScore(oteam) > plugin.getContestManager().getScore(
                plugin.getContestManager().getWinningTeam())) {
            Bukkit.getPluginManager().callEvent(new WinningTeamChangeEvent(oteam, plugin.getContestManager().getWinningTeam()));
        }

        // possibly show a killfeed?
        assert killer != null;
        Component kf = Component.text(killed.getName() + " has been slain by: "
                        + killer.getName()).color(NamedTextColor.GRAY);
        Bukkit.getServer().broadcast(kf);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (!p.hasPlayedBefore()) {
            p.sendMessage("Welcome to Server");
            p.sendMessage(Component.text("You have been assigned to: "));

            plugin.getTeamManager().assignTeam(p);
            p.sendMessage(Component.text(plugin.getTeamManager().getPlayerStats(p).getTeam().toString()));
            Bukkit.broadcast(Component.text("Welcome new player: " + p.getName()));
            plugin.getContestManager().getWinningTeamBar().showFor(p);
            return;
        }
        Bukkit.broadcast(Component.text("Welcome back " + p.getName()));
        plugin.getContestManager().getWinningTeamBar().showFor(p);
    }
}
