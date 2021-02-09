/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby;

import net.craftions.lobby.api.Download;
import net.craftions.lobby.commands.CommandSetSpawn;
import net.craftions.lobby.commands.CommandSpawn;
import net.craftions.lobby.config.Config;
import net.craftions.lobby.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Lobby extends JavaPlugin {

    private static Lobby plugin;
    private String prefix = "";
    public static Lobby getInstance(){
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        File systemConf = new File("./plugins/Lobby/system.yml");
        File messageConf = new File("./plugins/lobby/messages.yml");
        File locationConf = new File("./plugins/lobby/locations.yml");
        File menuConf = new File("./plugins/lobby/menu.yml");
        if(createFolderRoot(systemConf) && !systemConf.exists()){
            Download.download("https://cdn.craftions.net/plugins/Lobby/default/system.clean.yml", systemConf);
        }
        if(createFolderRoot(messageConf) && !messageConf.exists()){
            Download.download("https://cdn.craftions.net/plugins/Lobby/default/messages.clean.yml", messageConf);
        }
        if(createFolderRoot(locationConf) && !locationConf.exists()){
            Download.download("https://cdn.craftions.net/plugins/Lobby/default/locations.clean.yml", locationConf);
        }
        if(createFolderRoot(menuConf) && !menuConf.exists()){
            Download.download("https://cdn.craftions.net/plugins/Lobby/default/menu.clean.yml", menuConf);
        }
        Config sysConf = new Config(systemConf, "system");
        Config msgConf = new Config(messageConf, "message");
        Config locConf = new Config(locationConf, "locations");
        Config menConf = new Config(menuConf, "menu");
        this.prefix = (String) sysConf.get("prefix");
        this.prefix = this.prefix.replaceAll("&", "§");
        Bukkit.getPluginManager().registerEvents(new EventBlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerDisconnect(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerDamage(), this);
        Bukkit.getPluginManager().registerEvents(new EventFoodLevelChange(), this);
        Bukkit.getPluginManager().registerEvents(new EventInventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerInteract(), this);
        Bukkit.getPluginManager().registerEvents(new EventSignEdit(), this);
        Bukkit.getPluginManager().registerEvents(new EventBlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new EventItemDrop(), this);
        getCommand("setspawn").setExecutor(new CommandSetSpawn());
        getCommand("spawn").setExecutor(new CommandSpawn());
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        System.out.println("Loaded " + this.getDescription().getName() + " v" + this.getDescription().getVersion() + " by" + this.getDescription().getAuthors().toString());
        super.onEnable();
    }

    public Boolean createFolderRoot(File f){
        if(!f.getParentFile().isDirectory()){
            f.getParentFile().mkdirs();
            return false;
        }
        return true;
    }

    public String getPrefix() {
        return prefix;
    }
}
