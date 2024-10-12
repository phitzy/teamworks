package me.spf.teamworks.invasion.cmd;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.invasion.InvadingGroup;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InvitePlayerCommand implements CommandExecutor {

    private Map<UUID, UUID> invites = new HashMap<>();
    private final Teamworks plugin;

    public InvitePlayerCommand(Teamworks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {

            if (!(plugin.getTeamManager().getPlayerStats(player).inGroup())) {
                InvadingGroup group = new InvadingGroup(plugin.getTeamManager(), List.of(player));
            }

            String targetPlayerName = strings[0];
            Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
            if (targetPlayer == null) {
                player.sendMessage(Component.text("Player is not online."));
                return true;
            }

            invites.put(targetPlayer.getUniqueId(), targetPlayer.getUniqueId());
            targetPlayer.sendMessage(player.getName() + " has invited you to their group! Use /accept or /decline.");
            player.sendMessage(Component.text("You invited " + targetPlayerName + "."));
            return true;
        }

        return false;
    }

    public UUID getInviter(UUID target) {
        return invites.get(target);
    }

    public void removeInvite(UUID target) {
        invites.remove(target);
    }
}
