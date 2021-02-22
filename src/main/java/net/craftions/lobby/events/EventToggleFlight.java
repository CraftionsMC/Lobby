package net.craftions.lobby.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class EventToggleFlight implements Listener {
    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.ADVENTURE) || e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            e.setCancelled(true);
            if (e.getPlayer().getFallDistance() > 0.25) return;
            if (e.getPlayer().getLocation().clone().subtract(0, 2, 0).getBlock().getType() == Material.AIR) return;
            Location loc = e.getPlayer().getEyeLocation().clone();
            loc.setPitch(-30F);
            e.getPlayer().setVelocity(loc.getDirection().normalize().multiply(1.5));
        }
    }
}
