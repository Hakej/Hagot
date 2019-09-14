package me.hakej.hagot.hagot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MyEvents implements Listener {

    private static final Material BANNED_MATERIAL = Material.TNT;

    @EventHandler
    public void chatCheck(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        if (message.contains("wow")) {
            System.out.println("Someone is astonished. Probably by this mod.");
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
