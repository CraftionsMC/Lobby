/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.commands;

import net.craftions.lobby.Lobby;
import net.craftions.lobby.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(args.length == 0){
                Config.getInstance("locations").set("spawn", ((Player) sender).getLocation());
                Config.getInstance("locations").reload(true);
                String msg = (String) Config.getInstance("message").get("success");
                msg = msg.replaceAll("&", "§");
                sender.sendMessage(Lobby.getInstance().getPrefix() + msg);
            }else {
                sender.sendMessage(Lobby.getInstance().getPrefix() + "§cPlease use §e" + command.getUsage());
            }
        }else {
            sender.sendMessage(Lobby.getInstance().getPrefix() + "§cYou need to be a player.");
        }
        return true;
    }
}
