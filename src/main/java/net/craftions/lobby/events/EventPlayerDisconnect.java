/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import net.craftions.lobby.config.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventPlayerDisconnect implements Listener {

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e){
        String msg = (String) Config.getInstance("message").get("left");
        msg = msg.replaceAll("&", "§");
        msg = msg.replaceAll("%player", e.getPlayer().getName());
        e.setQuitMessage(msg);
    }
}
