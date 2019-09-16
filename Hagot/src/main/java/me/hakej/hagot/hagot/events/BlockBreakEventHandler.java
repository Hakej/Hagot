package me.hakej.hagot.hagot.events;

import me.hakej.hagot.hagot.ChatColoring;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakEventHandler implements SpigotEventHandler {

    public static void handle(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();
        player.sendMessage(ChatColoring.MOD + "You have broke: " + ChatColoring.MARKED + material.name());
    }
}
