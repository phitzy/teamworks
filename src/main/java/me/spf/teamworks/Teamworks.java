package me.spf.teamworks;

import me.spf.teamworks.command.*;
import me.spf.teamworks.gui.WinningTeamBar;
import me.spf.teamworks.invasion.cmd.InvadeCommand;
import me.spf.teamworks.gui.TeamGUI;
import me.spf.teamworks.invasion.cmd.InvitePlayerCommand;
import me.spf.teamworks.invasion.cmd.ResponseCommand;
import me.spf.teamworks.invasion.recipe.InvasionTokenRecipe;
import me.spf.teamworks.listener.GSListener;
import me.spf.teamworks.listener.PlayerListener;
import me.spf.teamworks.listener.TeamListener;
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

    public ContestManager getContestManager() {
        return contestManager;
    }

    public InvasionManager getInvasionManager() {
        return invasionManager;
    }

    public TeamGUI getTeamGUI() { return teamGUI; }


    @Override
    public void onEnable() {
        getLogger().info("Registering managers... ");
        teamManager = new TeamManager(this);
        contestManager = new ContestManager(this);
        invasionManager = new InvasionManager(this);
        teamGUI = new TeamGUI(this);

        getLogger().info("Registering bossbars...");
        new WinningTeamBar(this);

        GSListener gsListener = new GSListener(new GeneralStats());
        PlayerListener playerListener = new PlayerListener(this);
        TeamListener teamListener = new TeamListener(this);

        // register listeners
        getLogger().info("Registering listeners....");
        Bukkit.getPluginManager().registerEvents(gsListener, this);
        Bukkit.getPluginManager().registerEvents(playerListener, this);
        Bukkit.getPluginManager().registerEvents(teamListener, this);

        getLogger().info("Registering commands....");
        InvitePlayerCommand invitePlayerCommand = new InvitePlayerCommand(this);
        getCommand("invite").setExecutor(invitePlayerCommand);
        getCommand("accept").setExecutor(new ResponseCommand(this, invitePlayerCommand));
        getCommand("deny").setExecutor(new ResponseCommand(this, invitePlayerCommand));
        getCommand("jointeam").setExecutor(new JoinTeamCommand(this));
        getCommand("leaveteam").setExecutor(new LeaveTeamCommand(this));
        // getCommand("invade").setExecutor(new InvadeCommand(this));
        // getCommand("teams").setExecutor(new TeamsCommand(this));
        getCommand("myteam").setExecutor(new MyTeamCommand(this));
        getCommand("group").setExecutor(new GroupCommand(this));

        getLogger().info("Registering recipes....");
        new InvasionTokenRecipe(this);

    }

    @Override
    public void onDisable() {
    }
}
