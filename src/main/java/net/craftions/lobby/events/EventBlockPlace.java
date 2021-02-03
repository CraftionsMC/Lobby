/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            e.setCancelled(true);
        }
    }
}
