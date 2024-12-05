package me.spf.teamworks.command;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.managers.TeamManager;
import me.spf.teamworks.stats.PlayerStats;
import me.spf.teamworks.team.Team;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PlayerLookupCommand implements CommandExecutor {

    private final Teamworks plugin;

    public PlayerLookupCommand(Teamworks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage("Usage: /lookup <player>");
            return false;
        }

        String targetPlayerName = strings[0];
        PlayerStats stats = plugin.getTeamManager().getPlayerStats(targetPlayerName);

        if (stats == null) {
            commandSender.sendMessage("Player not found");
            return false;
        }

        commandSender.sendMessage("Player: " + targetPlayerName);
        commandSender.sendMessage("Team: " + stats.getTeam());
        commandSender.sendMessage("Score: " + stats.getScore());
        //TODO: display custom stat rendering
        return true;
    }
}
