/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.commands;

import net.craftions.lobby.Lobby;
import net.craftions.lobby.config.Config;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            ((Player) sender).teleport((Location) Config.getInstance("locations").get("spawn"));
        }else {
            sender.sendMessage(Lobby.getInstance().getPrefix() + "§cYou need to be a player.");
        }
        return true;
    }
}
