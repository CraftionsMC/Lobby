/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.signs;

import net.craftions.lobby.api.BungeeCordConnector;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class Signs {

    public static void regenerateSign(Sign s){
        String bungeecord_server = s.getLine(1);
        String description = s.getLine(2);
        s.setLine(0, "[§aWARP§r]");
        s.setLine(1, bungeecord_server);
        s.setLine(2, "§o" + description);
    }

    public static void processSign(Sign s, Player p){
        if(s.getLine(0).equals("[§aWARP§r]")){
            BungeeCordConnector.connect(p, s.getLine(1));
        }
    }
}