/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class EventBlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            e.setCancelled(true);
        }
    }
}
