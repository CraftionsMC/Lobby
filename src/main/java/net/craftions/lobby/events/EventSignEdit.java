/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class EventSignEdit implements Listener {

    @EventHandler
    public void onSignEdit(SignChangeEvent e){
        if(e.getLine(0).equals("[Warp]")){
            Sign sign = (Sign) e.getBlock().getState();
            e.setLine(0, "[§aWarp§r]");
            e.setLine(1, e.getLine(1));
            e.setLine(2, e.getLine(2));
        }
    }
}
