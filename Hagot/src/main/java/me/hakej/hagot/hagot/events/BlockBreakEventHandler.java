package me.hakej.hagot.hagot.events;

import me.hakej.hagot.hagot.ChatColoring;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockBreakEventHandler implements SpigotEventHandler {

    public static void handle(JavaPlugin plugin, BlockBreakEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();

        FileConfiguration config = plugin.getConfig();
        boolean enabled = config.getBoolean("event-toggle.break");

        if (enabled) {
            String materialMessage = ChatColoring.INFO + material.name() + ChatColoring.RESET;
            player.sendMessage("You have broke: " + materialMessage + ".");
        }
    }
}
