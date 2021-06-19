/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import net.craftions.lobby.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EventInventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        try {
            if(Menu.tpTo.containsKey(e.getCurrentItem())){
                Location loc = Menu.tpTo.get(e.getCurrentItem());
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().teleport(loc);
                e.setCancelled(true);
            } else {
                switch (e.getCurrentItem().getItemMeta().getDisplayName()){
                    case "§cNo players":
                        for(Player p : Bukkit.getOnlinePlayers()){
                            ((Player) e.getWhoClicked()).hidePlayer(p);
                        }
                        e.getWhoClicked().closeInventory();
                        break;
                    case "§6Staff only":
                        for(Player p : Bukkit.getOnlinePlayers()){
                            ((Player) e.getWhoClicked()).showPlayer(p);
                            if(!p.hasPermission("craftions.staff")){
                                ((Player) e.getWhoClicked()).hidePlayer(p);
                            }
                        }
                        e.getWhoClicked().closeInventory();
                        break;
                    case "§aAll players":
                        for(Player p : Bukkit.getOnlinePlayers()){
                            ((Player) e.getWhoClicked()).showPlayer(p);
                        }
                        e.getWhoClicked().closeInventory();
                        break;
                    default:
                        break;
                }
            }
        }catch (NullPointerException ex){

        }
    }
}
