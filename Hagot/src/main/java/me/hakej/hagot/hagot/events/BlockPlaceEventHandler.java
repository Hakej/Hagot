package me.hakej.hagot.hagot.events;

import me.hakej.hagot.hagot.ChatColoring;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockPlaceEventHandler implements SpigotEventHandler {

    private static final List<Material> BANNED_MATERIALS = Collections.unmodifiableList(
            new ArrayList<Material>() {{
                add(Material.TNT);
                add(Material.FIRE);
            }});

    public static void handle(JavaPlugin plugin, BlockPlaceEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();

        FileConfiguration config = plugin.getConfig();
        boolean enabled = config.getBoolean("event-toggle.place");

        if (enabled) {
            for (Material bannedMaterial : BANNED_MATERIALS) {
                if (material.equals(bannedMaterial)) {
                    ChatColor messageColor = ChatColoring.NEGATIVE;
                    String materialInfo = ChatColoring.INFO + bannedMaterial.toString() + messageColor;
                    block.setType(Material.AIR);
                    player.sendMessage(messageColor + "You cannot place " + materialInfo + "!");
                    break;
                }
            }
        }
    }
}
