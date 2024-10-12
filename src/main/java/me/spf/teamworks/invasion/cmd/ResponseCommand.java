package me.spf.teamworks.invasion.cmd;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.stats.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ResponseCommand implements CommandExecutor {

    private final Teamworks plugin;
    private InvitePlayerCommand invitePlayerCommand;

    public ResponseCommand(Teamworks plugin, InvitePlayerCommand invitePlayerCommand) {
        this.plugin = plugin;
        this.invitePlayerCommand = invitePlayerCommand;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            UUID playerUUID = player.getUniqueId();
            if (command.getName().equalsIgnoreCase("accept")) {
                UUID inviter = invitePlayerCommand.getInviter(playerUUID);
                if (inviter != null) {
                    Player inviterPlayer = Bukkit.getPlayer(inviter);
                    if (inviterPlayer != null) {
                        player.sendMessage("You have accepted the invite from " + inviterPlayer.getName() + "!");
                        inviterPlayer.sendMessage(player.getName() + " has joined your group!");

                        // handle group
                        PlayerStats ps = plugin.getTeamManager().getPlayerStats(inviterPlayer);
                        ps.getGroup().addPlayerToGroup(player);
                    }


                    invitePlayerCommand.removeInvite(playerUUID);
                } else {
                    player.sendMessage("You have no invites!");
                }
            } else if (command.getName().equalsIgnoreCase("decline")) {
                UUID inviter = invitePlayerCommand.getInviter(playerUUID);
                if (inviter != null) {
                    Player inviterPlayer = Bukkit.getPlayer(inviter);
                    if (inviterPlayer != null) {
                        player.sendMessage("You have declined the invite from " + inviterPlayer.getName() + "!");
                        inviterPlayer.sendMessage(player.getName() + " has declined your invite.");
                    }
                    invitePlayerCommand.removeInvite(playerUUID);
                } else {
                    player.sendMessage("You have no invites!");
                }
            }
        }
        return true;
    }
}
