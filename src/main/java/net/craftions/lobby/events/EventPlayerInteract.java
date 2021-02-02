/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import net.craftions.lobby.config.Config;
import net.craftions.lobby.menu.Menu;
import net.craftions.lobby.signs.Signs;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventPlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        try {
            if(e.getClickedBlock().getType().equals(Material.OAK_SIGN)){
                Signs.processSign((Sign) e.getClickedBlock().getState(), e.getPlayer());
            }
        }catch (NullPointerException ex){
            try {
                String display = (String) Config.getInstance("menu").get("item_name");
                display = display.replaceAll("&", "§");
                if(e.getItem().getItemMeta().getDisplayName().equals(display)) {
                    e.getPlayer().openInventory(Menu.getMenu());
                }
            }catch (NullPointerException ex0){ }
        }
    }
}
