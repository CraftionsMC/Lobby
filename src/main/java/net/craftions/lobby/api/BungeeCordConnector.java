/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.api;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.craftions.lobby.Lobby;
import org.bukkit.entity.Player;

public class BungeeCordConnector {

    public static void connect(Player player, String server){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(Lobby.getInstance(), "BungeeCord", out.toByteArray());
    }
}
