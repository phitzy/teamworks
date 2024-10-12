package me.spf.teamworks.listener;

import me.spf.teamworks.stats.GeneralStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class GSListener implements Listener {
    private final GeneralStats stats;

    public GSListener(GeneralStats stats) {
        this.stats = stats;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        stats.setBlocksBrokenByPlayers(
                stats.getBlocksBrokenByPlayers() + 1
        );
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event instanceof Player) {
            stats.setTotalPlayerDeaths(stats.getTotalPlayerDeaths() + 1);
            return;
        }
        stats.setTotalEntityDeaths(stats.getTotalEntityDeaths() + 1);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

}
