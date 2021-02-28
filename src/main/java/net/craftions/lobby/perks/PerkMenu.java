/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.perks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PerkMenu {

    public static Inventory getMenu(Player p){
        Inventory i = Bukkit.createInventory(null, InventoryType.CHEST);

        ItemStack fly = new ItemStack(Material.FEATHER);

        ItemMeta flyMeta = fly.getItemMeta();
        flyMeta.setDisplayName("§bFliegen");
        List<String> flyLore = new ArrayList<>();
        if(!p.hasPermission("craftions.lobby.perks.fly")){
            flyLore.add("§cNicht verfügbar!");
        }
        if(p.getAllowFlight()){
            flyLore.add("§5Status: §2aktiviert");
        }else {
            flyLore.add("§5Status: §cdeaktiviert");
        }
        flyMeta.setLore(flyLore);
        fly.setItemMeta(flyMeta);

        i.setItem(0, fly);
        return i;
    }
}
