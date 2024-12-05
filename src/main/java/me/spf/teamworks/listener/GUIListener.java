package me.spf.teamworks.listener;

import me.spf.teamworks.Teamworks;
import me.spf.teamworks.team.TeamName;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public final class GUIListener implements Listener {

    private final Teamworks plugin;

    public GUIListener(Teamworks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Team Stats")) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);
        if (event.getClickedInventory().getType() == InventoryType.PLAYER) return;

        switch (event.getSlot()) {
            case 0 -> {
                plugin.getTeamManager().assignTeam(p, TeamName.RED);
                p.sendMessage(Component.text("You have joined team: " + TeamName.RED));
            }
            case 1 -> {
                plugin.getTeamManager().assignTeam(p, TeamName.BLUE);
                p.sendMessage(Component.text("You have joined team: " + TeamName.BLUE));
            }
            case 2 -> {
                plugin.getTeamManager().assignTeam(p, TeamName.GREEN);
                p.sendMessage(Component.text("You have joined team: " + TeamName.GREEN));
            }
            case 3 -> {
                plugin.getTeamManager().assignTeam(p, TeamName.YELLOW);
                p.sendMessage(Component.text("You have joined team: " + TeamName.BLUE));
            }
        }

    }

}
