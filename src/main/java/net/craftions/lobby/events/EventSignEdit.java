/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import net.craftions.lobby.signs.Signs;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class EventSignEdit implements Listener {

    @EventHandler
    public void onSignEdit(SignChangeEvent e){
        if(e.getBlock().getType().equals(Material.OAK_SIGN)){
            Sign sign = (Sign) e.getBlock().getState();
            if(sign.getLine(0).equals("[Warp]")){
                Signs.regenerateSign(sign);
            }
        }
    }
}
