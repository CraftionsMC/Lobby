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
        String msg = (String) Config.getInstance("message").get("join");
        msg = msg.replaceAll("&", "§");
        msg = msg.replaceAll("%player", e.getPlayer().getName());
        e.setJoinMessage(msg);
        e.getPlayer().teleport((Location) Config.getInstance("locations").get("spawn"));
        e.getPlayer().setInvulnerable(true);
    }
}
