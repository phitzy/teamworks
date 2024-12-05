package me.spf.teamworks.util;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class WelcomeBook {

    public static final ItemStack BOOK = new ItemStack(Material.WRITTEN_BOOK);;
    public static final BookMeta META = (BookMeta) BOOK.getItemMeta();;

    static {
        createWelcomeBook();
    }

    private static void createWelcomeBook() {
        // Create the book item

        if (META != null) {
            // Set book properties
            META.setTitle("Read Me");
            META.setAuthor("Teamworks");

            // Add pages
            // TODO: Use component system
            META.setPages(
                    ChatColor.BOLD + "Welcome to Our Server!" + ChatColor.RESET + "\n\n" +
                            "We're excited to have you here. This guide will help you get started!",

                    ChatColor.BOLD + "Getting Started\n" + ChatColor.RESET +
                            "1. Explore spawn.\n" +
                            "2. Read the rules.\n" +
                            "3. Use /help for commands.\n\n" +
                            "Have fun and enjoy your time!",

                    ChatColor.BOLD + "Contact Info\n" + ChatColor.RESET +
                            "Discord: discord.gg/yourserver\n" +
                            "Website: www.yourserver.com\n\n" +
                            "Need help? Ask staff!"
            );

            BOOK.setItemMeta(META);
        }

    }

}
