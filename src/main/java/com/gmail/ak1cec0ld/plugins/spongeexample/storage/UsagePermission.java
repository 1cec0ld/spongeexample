package com.gmail.ak1cec0ld.plugins.spongeexample.storage;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

public class UsagePermission {



    public static boolean checkPlayer(Player player, String region){
        return player.hasPermission(region + ".used");
    }

    public static void setPlayer(Player player, String region){
        StringBuilder builder = new StringBuilder();
        builder.append("lp user ")
                .append(player.getName())
                .append(" permission set ")
                .append(region+".used true");
        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), builder.toString());
    }

}
