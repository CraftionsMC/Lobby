package net.craftions.lobby.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventFarmlandDestroy implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);

        if (event.getAction() == (Action.PHYSICAL) && block.getType() == Material.FARMLAND) {
            event.setCancelled(true);
        }
    }
}
