/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import net.craftions.lobby.api.BungeeCordConnector;
import net.craftions.lobby.config.Config;
import net.craftions.lobby.menu.Menu;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventPlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getClickedBlock() != null){
            if(e.getClickedBlock().getType().equals(Material.OAK_WALL_SIGN)){
                BungeeCordConnector.connect(e.getPlayer(), ((Sign) e.getClickedBlock().getState()).getLine(1));
            }
        }
        if(e.getItem() != null) {
            String display = (String) Config.getInstance("menu").get("item_name");
            display = display.replaceAll("&", "§");
            if(e.getItem().getItemMeta().getDisplayName().equals(display)) {
                e.getPlayer().openInventory(Menu.getMenu());
                e.setCancelled(true);
            }
        }
    }
}
