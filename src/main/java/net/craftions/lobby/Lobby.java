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
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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
                byte[] content = Files.readAllBytes(Path.of(getClass().getResource("/system.clean.yml").getPath()));
                systemConf.createNewFile();
                FileWriter w = new FileWriter(systemConf);
                w.write(new String(content, StandardCharsets.UTF_8));
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(createFolderRoot(messageConf)){
            try {
                byte[] content = Files.readAllBytes(Path.of(getClass().getResource("/messages.clean.yml").getPath()));
                systemConf.createNewFile();
                FileWriter w = new FileWriter(messageConf);
                w.write(new String(content, StandardCharsets.UTF_8));
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(createFolderRoot(locationConf)){
            try {
                byte[] content = Files.readAllBytes(Path.of(getClass().getResource("/locations.clean.yml").getPath()));
                systemConf.createNewFile();
                FileWriter w = new FileWriter(locationConf);
                w.write(new String(content, StandardCharsets.UTF_8));
                w.close();
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
