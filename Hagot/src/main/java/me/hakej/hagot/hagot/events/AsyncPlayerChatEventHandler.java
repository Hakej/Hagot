package me.hakej.hagot.hagot.events;

import me.hakej.hagot.hagot.ChatColoring;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class AsyncPlayerChatEventHandler implements SpigotEventHandler {

    public static void handle(JavaPlugin plugin, AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();

        FileConfiguration config = plugin.getConfig();
        boolean enabled = config.getBoolean("toggle.censor-words");
        List<String> bannedWords = config.getStringList("banned-words");

        if (enabled) {
            for (String bannedWord : bannedWords) {
                if (message.contains(bannedWord)) {
                    // Hide message
                    event.setCancelled(true);
                    player.sendMessage(ChatColoring.NEGATIVE + "You cannot say that!");
                }
            }
        }
    }
}
