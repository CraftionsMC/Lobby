package net.craftions.lobby.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class EventChangeGameMode implements Listener {
    @EventHandler
    public void onChangeGameMode(PlayerGameModeChangeEvent e) {
        if (e.getNewGameMode().equals(GameMode.ADVENTURE) || e.getNewGameMode().equals(GameMode.SURVIVAL)) {
            e.getPlayer().setAllowFlight(true);
        }
    }
}
