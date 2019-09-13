package me.hakej.hagot.hagot;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hagot extends JavaPlugin {

    private ConsolePrinter consolePrinter = new ConsolePrinter(getServer().getConsoleSender(), this);

    @Override
    public void onEnable() {
        consolePrinter.sendStartConsoleMessage();
        initializeCommands();
    }

    private void initializeCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("healme").setExecutor(new HealMeCommand());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + this.getName() + " disabled.");
    }
}
