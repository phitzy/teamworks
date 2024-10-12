package me.spf.teamworks.listener;

import me.spf.teamworks.Teamworks;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TeamListener implements Listener {

    private final Teamworks plugin;

    public TeamListener(Teamworks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(Component.text("Team Stats").color(NamedTextColor.AQUA).toString())) {
            event.setCancelled(true); // Prevent moving items
            // Handle item clicks
            event.getWhoClicked().sendMessage(Component.text("You clicked on a team!").color(NamedTextColor.YELLOW));
        }
    }

}
