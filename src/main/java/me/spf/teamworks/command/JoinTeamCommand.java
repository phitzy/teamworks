package me.spf.teamworks.command;

import me.spf.teamworks.team.TeamName;
import me.spf.teamworks.Teamworks;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JoinTeamCommand implements CommandExecutor {

    private final Teamworks plugin;

    public JoinTeamCommand(Teamworks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {

            if (strings.length == 0) {

                plugin.getTeamManager().assignTeam(player);
                plugin.getLogger().info("Assigned team to player: " + player.getName());
                Component component = Component.text("You have joined: " + plugin.getTeamManager().getPlayerTeam(player)).color(
                        plugin.getTeamGUI().getTeamNameToColor().get(
                                plugin.getTeamManager().getPlayerTeam(player).toString()
                        )
                );
                player.sendMessage(component);
                return true;
            }
            plugin.getTeamManager().assignTeam(player, TeamName.valueOf(strings[0]));
            plugin.getLogger().fine("Assigned team to player: " + player.getName());
            Component component = Component.text("You have joined: " + plugin.getTeamManager().getPlayerTeam(player)).color(
                    plugin.getTeamGUI().getTeamNameToColor().get(
                            plugin.getTeamManager().getPlayerTeam(player).toString()
                    )
            );
            player.sendMessage(component);
            return true;
        }
        return false;
    }
}
