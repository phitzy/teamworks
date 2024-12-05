package me.spf.teamworks.invasion.cmd;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.invasion.InvadingGroup;
import me.spf.teamworks.invasion.InvasionTask;
import me.spf.teamworks.managers.InvasionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InvadeCommand implements CommandExecutor {

    private final Teamworks plugin;

    public InvadeCommand(Teamworks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender,
                             @NotNull Command command, @NotNull String s,
                             @NotNull String[] strings) {
        if (commandSender instanceof Player invader) {
            if (strings.length != 1) {
                invader.sendMessage("Usage: /invade <team>");
                return false;
            }
            InvadingGroup group = plugin.getTeamManager().getPlayerStats(invader).getGroup();
            plugin.getInvasionManager().invadingGroups.add(group);
            new InvasionTask(group, plugin).run();
            return true;
        }

        return false;
    }
}
