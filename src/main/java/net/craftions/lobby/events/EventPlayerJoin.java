/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import net.craftions.lobby.config.Config;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventPlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(((String) Config.getInstance("message").get("join")).replaceAll("%player", e.getPlayer().getName()).replaceAll("&", "§"));
        e.getPlayer().teleport((Location) Config.getInstance("locations").get("spawn"));
    }
}
