/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.menu;

import net.craftions.lobby.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Menu {

    public static HashMap<ItemStack, Location> tpTo = new HashMap<>();

    public static Inventory getMenu(){
        String title = (String) Config.getInstance("menu").get("menu_title");
        title = title.replaceAll("&", "§");
        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, title);
        for(String s : Config.getInstance("menu").getAll("content", false)){
            System.out.println("Addind item " + s);
            int slot = (int) Config.getInstance("menu").get("content." + s + ".slot");
            Material type = Material.getMaterial((String) Config.getInstance("menu").get("content." + s + ".type"));
            String display = (String) Config.getInstance("menu").get("content." + s + ".display");
            display = display.replaceAll("&", "§");
            ItemStack item = new ItemStack(type);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(display);
            item.setItemMeta(meta);
            if(((String) Config.getInstance("menu").get("content." + s + ".action")).equals("teleport")){
                Location teleport = (Location) Config.getInstance("menu").get("content." + s + ".teleport");
                tpTo.put(item, teleport);
            }
            inv.setItem(slot, item);
        }
        return inv;
    }
}
