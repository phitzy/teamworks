package me.spf.teamworks.gui.command;

import me.spf.teamworks.gui.TeamSelectionGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeamSelectCommand implements CommandExecutor {

    private final TeamSelectionGUI gui;

    public TeamSelectCommand(TeamSelectionGUI gui) {
        this.gui = gui;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            gui.openGUI(player);
            return true;
        }
        return false;
    }
}
