package me.spf.teamworks.command;

import me.spf.teamworks.constants.MapConstants;
import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.Teamworks;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MyTeamCommand implements CommandExecutor {

    private final Teamworks plugin;

    public MyTeamCommand(Teamworks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            TeamName teamName = plugin.getTeamManager().getPlayerTeam(player);
            Component component = Component.text("You are on team: " + teamName).color(MapConstants.getTeamNameToColor()
                    .get(teamName.toString()));
            player.sendMessage(component);
            return true;
        }
        return false;
    }
}
