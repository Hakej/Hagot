package me.hakej.hagot.hagot;

import me.hakej.hagot.hagot.events.AsyncPlayerChatEventHandler;
import me.hakej.hagot.hagot.events.BlockBreakEventHandler;
import me.hakej.hagot.hagot.events.BlockPlaceEventHandler;
import me.hakej.hagot.hagot.events.PlayerJoinEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ModEventHandler implements Listener {

    private JavaPlugin plugin;

    public ModEventHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void asyncPlayerChat(AsyncPlayerChatEvent event) {
        AsyncPlayerChatEventHandler.handle(plugin, event);
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        BlockBreakEventHandler.handle(plugin, event);
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        BlockPlaceEventHandler.handle(plugin, event);
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        PlayerJoinEventHandler.handle(plugin, event);
    }
}
