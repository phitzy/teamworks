package me.spf.teamworks.command;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.gui.TeamGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeamsCommand implements CommandExecutor {
    private final Teamworks plugin;
    public TeamsCommand(Teamworks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {
            plugin.getTeamGUI().openTeamGUI(player);
            return true;
        }

        commandSender.sendMessage("This command can only be run by players.");
        return false;

    }
}
