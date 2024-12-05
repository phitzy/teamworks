package me.spf.teamworks.command;

import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.Teamworks;
import me.spf.teamworks.stats.PlayerStats;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LeaveTeamCommand implements CommandExecutor {

    private final Teamworks plugin;

    public LeaveTeamCommand(Teamworks plugin) {
        this.plugin = plugin;
    }

    /**
     * TODO: Prevent spam leaving / joining
     * @param commandSender
     * @param command
     * @param s
     * @param strings
     * @return
     */
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            player.sendMessage(Component.text("You have left the team."));
            plugin.getTeamManager().removeFromTeam(player);
        }
        return false;
    }
}
