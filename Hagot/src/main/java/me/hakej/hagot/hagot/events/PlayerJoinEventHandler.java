package me.hakej.hagot.hagot.events;

import me.hakej.hagot.hagot.ChatColoring;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerJoinEventHandler implements SpigotEventHandler {

    public static void handle(JavaPlugin plugin, PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String pluginName = plugin.getName();
        String pluginVersion = plugin.getDescription().getVersion();
        player.sendMessage(
                ChatColoring.MOD + pluginName + " " +
                        ChatColoring.INFO + pluginVersion +
                        ChatColoring.RESET + " is " +
                        ChatColoring.POSITIVE + "enabled" +
                        ChatColoring.RESET + "."
        );
    }
}
