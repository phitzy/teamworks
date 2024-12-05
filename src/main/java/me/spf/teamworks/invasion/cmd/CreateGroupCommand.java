package me.spf.teamworks.invasion.cmd;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.invasion.InvadingGroup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupCommand implements CommandExecutor {

    private final Teamworks plugin;

    public CreateGroupCommand(Teamworks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {

            List<Player> players = new ArrayList<>();
            players.add(player);

            InvadingGroup invadingGroup = new InvadingGroup(plugin.getTeamManager(), players);
            plugin.getInvasionManager().activeGroups.add(invadingGroup);
        }

        return false;
    }
}
