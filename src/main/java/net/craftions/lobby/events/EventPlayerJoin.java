/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import net.craftions.lobby.config.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventPlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        String msg = (String) Config.getInstance("message").get("join");
        msg = msg.replaceAll("&", "§");
        msg = msg.replaceAll("%player", e.getPlayer().getName());
        e.setJoinMessage(msg);
        e.getPlayer().teleport((Location) Config.getInstance("locations").get("spawn"));
        e.getPlayer().setInvulnerable(true);
        ItemStack menu = new ItemStack(Material.getMaterial((String) Config.getInstance("menu").get("item")));
        ItemMeta menuMeta = menu.getItemMeta();
        String display = (String) Config.getInstance("menu").get("item_name");
        display = display.replaceAll("&", "§");
        menuMeta.setDisplayName(display);
        menu.setItemMeta(menuMeta);
        e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setItem(0, menu);
        e.getPlayer().setAllowFlight(true);
    }
}
