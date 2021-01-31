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
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

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
            try {
                FileUtils.copyInputStreamToFile(this.getResource("/system.clean.yml"), new File("./plugins/Lobby/system.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(createFolderRoot(messageConf)){
            try {
                FileUtils.copyInputStreamToFile(this.getResource("/messages.clean.yml"), new File("./plugins/Lobby/messages.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(createFolderRoot(locationConf)){
            try {
                FileUtils.copyInputStreamToFile(this.getResource("/locations.clean.yml"), new File("./plugins/Lobby/locations.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
