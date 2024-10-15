package me.spf.teamworks.command;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.invasion.InvadingGroup;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GroupCommand implements CommandExecutor {
    private final Teamworks plugin;

    public GroupCommand(Teamworks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {

            InvadingGroup group = plugin.getTeamManager().getPlayerStats(player).getGroup();
            if (group == null) {
                player.sendMessage(Component.text("You are not in a group!").color(NamedTextColor.RED));
                return true;
            }

            player.sendMessage(Component.text("Your group: "));
            for (Player p : group.getPlayers()) {
                player.sendMessage(Component.text(p.getName()).color(NamedTextColor.YELLOW));
            }
            return true;
        }
        return false;
    }
}
