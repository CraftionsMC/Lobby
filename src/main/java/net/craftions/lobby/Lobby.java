/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby;

import net.craftions.lobby.commands.CommandSetSpawn;
import net.craftions.lobby.commands.CommandSpawn;
import net.craftions.lobby.config.Config;
import net.craftions.lobby.events.EventBlockBreak;
import net.craftions.lobby.events.EventPlayerDisconnect;
import net.craftions.lobby.events.EventPlayerJoin;
import net.craftions.lobby.util.Util;
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
        if(createFolderRoot(systemConf)){
            Util.copy(getClass().getResourceAsStream("/system.clean.yml"), systemConf.getParentFile().getAbsolutePath() + "/plugins/Lobby/system.yml");
        }
        if(createFolderRoot(messageConf)){
            Util.copy(getClass().getResourceAsStream("/messages.clean.yml"), systemConf.getParentFile().getAbsolutePath() + "/plugins/Lobby/messages.yml");
        }
        if(createFolderRoot(locationConf)){
            Util.copy(getClass().getResourceAsStream("/locations.clean.yml"), systemConf.getParentFile().getAbsolutePath() + "/plugins/Lobby/locations.yml");
        }
        Config sysConf = new Config(systemConf, "system");
        Config msgConf = new Config(messageConf, "message");
        Config locConf = new Config(locationConf, "locations");
        this.prefix = (String) sysConf.get("prefix");
        System.out.println("Loaded " + this.getDescription().getName() + " v" + this.getDescription().getVersion() + " by" + this.getDescription().getAuthors().toString());
        // register events
        Bukkit.getPluginManager().registerEvents(new EventBlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerDisconnect(), this);
        // commands
        getCommand("setspawn").setExecutor(new CommandSetSpawn());
        getCommand("spawn").setExecutor(new CommandSpawn());
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
