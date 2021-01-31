/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class EventFoodLevelChange implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e){
        e.setFoodLevel(20);
    }
}
