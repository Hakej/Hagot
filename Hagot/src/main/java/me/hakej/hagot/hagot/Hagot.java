package me.hakej.hagot.hagot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hagot extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + this.getName() + " enabled.");
        this.getCommand("test").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("test")) {
            Player player = Bukkit.getPlayer(sender.getName());
            if (player != null) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Test command works!!");
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + this.getName() + " disabled.");
        this.getCommand("test").setExecutor(this);
    }
}
