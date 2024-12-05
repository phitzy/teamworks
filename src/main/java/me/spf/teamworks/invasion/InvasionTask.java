package me.spf.teamworks.invasion;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.stats.PlayerStats;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is constructed with a group of players whenever the command is called. Players must
 * be on the same team to be in the same invasion group.
 * @author Sean F
 */
public class InvasionTask extends BukkitRunnable {
    private final InvadingGroup group;
    private final Teamworks plugin;

    public InvasionTask(InvadingGroup group, Teamworks plugin) {
        this.group = group;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Player target = findRandomTargetPlayer();
        assert target != null;
        Location targetLoc = target.getLocation();

        for (Player p : group.getPlayers()) {
            double offsetX = (Math.random() * 30) - 15;
            double offsetZ = (Math.random() * 30) - 15;
            Location newLoc = targetLoc.clone().add(offsetX, 0, offsetZ);
            while (!isLocationSafe(newLoc)) {
                newLoc = newLoc.clone().add(offsetX, 0, offsetZ);
                newLoc.setY(targetLoc.getWorld().getHighestBlockYAt(newLoc));
            }
            p.teleport(newLoc);
            p.sendMessage(Component.text("You have invaded near: " + target.getName())
                    .color(NamedTextColor.LIGHT_PURPLE));
            PlayerStats ps = plugin.getTeamManager().getPlayerStats(p);
            ps.setNumberOfInvasions(ps.getNumberOfInvasions() + 1);
        }

        startInvasionTimer(group);
        // TODO: change to team name
        target.sendMessage(Component.text
                ("You are being invaded by members of: "
                        + plugin.getTeamManager().getPlayerTeam(group.getPlayers().getFirst())).color(NamedTextColor.GRAY));

    }

    /**
     * This method is so jank I want to kill myself
     * @return Random online player of a different team
     */
    private Player findRandomTargetPlayer() {
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        Player test = rPlayer(playerList);
        // check to make sure test player is not on same team
        while (plugin.getTeamManager()
                .getPlayerTeam(test)
                .equals(plugin.getTeamManager()
                        .getPlayerTeam(group.getPlayers().getFirst()))) {

            test = rPlayer(playerList);

        }
        return test;
    }

    /**
     * Get random player from players list
     * @param playerList online player
     * @return
     */
    private static Player rPlayer(List<Player> playerList) {
        return playerList.get((int) (Math.random() * playerList.size()));
    }

    private void startInvasionTimer(InvadingGroup group) {
        int invasionTime = (int) (Math.random() * (6 - 4 + 1) + 4);
        final BossBar bossBar = BossBar.bossBar(Component.text("Time left: " + invasionTime),
                1, BossBar.Color.BLUE, BossBar.Overlay.NOTCHED_10);
        for (Player p : group.getPlayers()) {
            p.showBossBar(bossBar);
        }
        new BukkitRunnable() {
            int timeLeft = invasionTime * 60;
            @Override
            public void run() {
                if (timeLeft <= 0) {
                    // send group message
                    for (Player p : group.getPlayers()) {
                        p.hideBossBar(bossBar);
                        p.sendMessage(Component.text("Invasion time is up! Teleporting home...").color(NamedTextColor.RED));
                    }
                    cancel();
                } else {
                    timeLeft--;
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    private boolean isLocationSafe(Location loc) {
        // Check if the location is solid ground
        Material blockType = loc.getBlock().getType();
        return blockType.isSolid() && !blockType.equals(Material.WATER) && !blockType.equals(Material.LAVA);
    }


}
