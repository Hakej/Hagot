package me.hakej.hagot.hagot.events;

import me.hakej.hagot.hagot.ChatColoring;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockPlaceEventHandler implements SpigotEventHandler {

    private static final List<Material> BANNED_MATERIALS = Collections.unmodifiableList(
            new ArrayList<Material>() {{
                add(Material.TNT);
                add(Material.FIRE);
            }});

    public static void handle(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();

        for (Material bannedMaterial : BANNED_MATERIALS) {
            if (material.equals(bannedMaterial)) {
                block.setType(Material.AIR);
                player.sendMessage(ChatColoring.NEGATIVE + "You cannot place " + bannedMaterial + "!");
                break;
            }
        }
    }
}
