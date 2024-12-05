package me.spf.teamworks;

import me.spf.teamworks.command.*;
import me.spf.teamworks.gui.WinningTeamBar;
import me.spf.teamworks.invasion.cmd.InvadeCommand;
import me.spf.teamworks.gui.TeamGUI;
import me.spf.teamworks.invasion.cmd.InvitePlayerCommand;
import me.spf.teamworks.invasion.cmd.ResponseCommand;
import me.spf.teamworks.invasion.recipe.InvasionTokenRecipe;
import me.spf.teamworks.listener.*;
import me.spf.teamworks.managers.ContestManager;
import me.spf.teamworks.managers.InvasionManager;
import me.spf.teamworks.managers.TeamManager;
import me.spf.teamworks.stats.GeneralStats;
import me.spf.teamworks.stats.PlayerStats;
import me.spf.teamworks.team.Team;
import me.spf.teamworks.team.TeamName;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Teamworks extends JavaPlugin {

    private TeamManager teamManager;
    private ContestManager contestManager;
    private InvasionManager invasionManager;

    private TeamGUI teamGUI;

    private File playerDataFile;
    private FileConfiguration playerDataConfig;


    public TeamManager getTeamManager() {
        return teamManager;
    }

    public @NotNull ContestManager getContestManager() {
        return contestManager;
    }

    public InvasionManager getInvasionManager() {
        return invasionManager;
    }

    public TeamGUI getTeamGUI() { return teamGUI; }


    @Override
    public void onEnable() {
        getLogger().info("Registering managers... ");
        contestManager = new ContestManager(this);
        teamManager = new TeamManager(this);
        invasionManager = new InvasionManager(this);
        teamGUI = new TeamGUI(this);

        GSListener gsListener = new GSListener(new GeneralStats());
        PlayerListener playerListener = new PlayerListener(this);
        ContestListener contestListener = new ContestListener(this);
        GUIListener guiListener = new GUIListener(this);

        // register listeners
        getLogger().info("Registering listeners....");
        Bukkit.getPluginManager().registerEvents(gsListener, this);
        Bukkit.getPluginManager().registerEvents(playerListener, this);
        Bukkit.getPluginManager().registerEvents(contestListener, this);
        Bukkit.getPluginManager().registerEvents(guiListener, this);

        getLogger().info("Registering commands....");
        InvitePlayerCommand invitePlayerCommand = new InvitePlayerCommand(this);
        getCommand("invite").setExecutor(invitePlayerCommand);
        getCommand("accept").setExecutor(new ResponseCommand(this, invitePlayerCommand));
        getCommand("deny").setExecutor(new ResponseCommand(this, invitePlayerCommand));
        getCommand("jointeam").setExecutor(new JoinTeamCommand(this));
        getCommand("leaveteam").setExecutor(new LeaveTeamCommand(this));
        getCommand("invade").setExecutor(new InvadeCommand(this));
        getCommand("teams").setExecutor(new TeamsCommand(this));
        getCommand("myteam").setExecutor(new MyTeamCommand(this));
        getCommand("group").setExecutor(new GroupCommand(this));
        getCommand("lookup").setExecutor(new PlayerLookupCommand(this));

        getLogger().info("Registering recipes....");
        new InvasionTokenRecipe(this);

        getLogger().info("Handling bossbars...");
    }

    @Override
    public void onDisable() {
    }
}
