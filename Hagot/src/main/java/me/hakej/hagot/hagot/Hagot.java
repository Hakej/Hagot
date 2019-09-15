package me.hakej.hagot.hagot;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hagot extends JavaPlugin {

    private ConsolePrinter consolePrinter = new ConsolePrinter(this);
    private MyEvents myEvents = new MyEvents(this);

    @Override
    public void onEnable() {
        consolePrinter.sendStartConsoleMessage();
        initializeCommands();
        getConfig().options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(myEvents, this);
    }

    private void initializeCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("healme").setExecutor(new HealMeCommand());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + getName() + " disabled.");
    }
}
