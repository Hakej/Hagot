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
        String playerId = player.getUniqueId().toString();

        FileConfiguration config = plugin.getConfig();
        boolean censorBadWords = config.getBoolean("censor-bad-words");
        int incidents = config.getInt("incidents." + playerId);
        List<String> wordList = config.getStringList("banned-words");

        if (censorBadWords) {
            for (String bannedWord : wordList) {
                if (message.contains(bannedWord)) {
                    // Hide message
                    event.setCancelled(true);
                    player.sendMessage(ChatColoring.NEGATIVE + "You cannot say that!");
                    // Update and save amount of player's incidents
                    config.set("incidents." + playerId, ++incidents);
                    plugin.saveConfig();
                }
            }
        }
    }
}
