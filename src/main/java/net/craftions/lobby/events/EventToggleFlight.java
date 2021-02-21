package net.craftions.lobby.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class EventToggleFlight implements Listener {
    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.ADVENTURE) || e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            e.setCancelled(true);
            if (e.getPlayer().getFallDistance() != 0) return;
            if (e.getPlayer().getLocation().subtract(0, 2, 0).getBlock().getType() != Material.AIR) return;
            Location loc = e.getPlayer().getEyeLocation().clone();
            loc.setPitch(22.5F);
            Vector vec = loc.getDirection().normalize().multiply(2);
            e.getPlayer().setVelocity(vec);
            e.getPlayer().setFlying(false);
        }
    }
}
