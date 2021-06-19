/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerFilter {

    public static Inventory getMenu(){
        Inventory inv = Bukkit.createInventory(null, 9, "§6Player Filter");

        ItemStack nobody = new ItemStack(Material.BARRIER, 1);
        ItemStack team = new ItemStack(Material.HONEY_BLOCK, 1);
        ItemStack all = new ItemStack(Material.SLIME_BLOCK, 1);

        ItemMeta nobodyMeta = nobody.getItemMeta();
        ItemMeta teamMeta = team.getItemMeta();
        ItemMeta allMeta = all.getItemMeta();

        nobodyMeta.setDisplayName("§cNo players");
        teamMeta.setDisplayName("§6Staff only");
        allMeta.setDisplayName("§aAll players");

        nobody.setItemMeta(nobodyMeta);
        team.setItemMeta(teamMeta);
        all.setItemMeta(allMeta);

        inv.setItem(2, nobody);
        inv.setItem(4, team);
        inv.setItem(6, all);
        return inv;
    }
}
