package me.spf.teamworks.listener;

import me.spf.teamworks.Teamworks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public final class TeamListener implements Listener {

    private final Teamworks plugin;

    public TeamListener(Teamworks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

    }

}
