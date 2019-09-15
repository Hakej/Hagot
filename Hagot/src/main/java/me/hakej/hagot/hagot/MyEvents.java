package me.hakej.hagot.hagot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class MyEvents implements Listener {

    private static final Material BANNED_MATERIAL = Material.TNT;
    private JavaPlugin plugin;

    public MyEvents(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();

        boolean enabled = config.getBoolean("enabled");
        int incidents = config.getInt("incidents." + player.getUniqueId().toString());
        List<String> wordList = config.getStringList("banned-words");

        if (enabled) {
            for (String bannedWord : wordList) {
                if (message.contains(bannedWord)) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You cannot say that!");

                    if (incidents != 0) {
                        incidents++;
                        config.set("incidents." + player.getUniqueId().toString(), incidents);
                        plugin.saveConfig();
                    } else {
                        config.set("incidents." + player.getUniqueId().toString(), 1);
                        plugin.saveConfig();
                    }
                }
            }
        }
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.GRAY + "You have broke: " + ChatColor.YELLOW + material.name());
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();

        if (material.equals(BANNED_MATERIAL)) {
            block.setType(Material.AIR);
            player.sendMessage(ChatColor.RED + "You cannot place " + BANNED_MATERIAL + "!");
        }
    }
}
