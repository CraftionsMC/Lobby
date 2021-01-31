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
        e.setQuitMessage(((String) Config.getInstance("message").get("left")).replaceAll("%player", e.getPlayer().getName()).replaceAll("&", "§"));
    }
}
