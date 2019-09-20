package me.hakej.hagot.hagot;

import me.hakej.hagot.hagot.commands.HealMe;
import me.hakej.hagot.hagot.commands.Test;
import me.hakej.hagot.hagot.commands.Toggle;
import me.hakej.hagot.hagot.console.ConsolePrinter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hagot extends JavaPlugin {

    private ConsolePrinter consolePrinter = new ConsolePrinter(this);
    private ModEventHandler modEventHandler = new ModEventHandler(this);

    @Override
    public void onEnable() {
        consolePrinter.enableMessage();
        initializePlugin();
    }

    private void initializePlugin() {
        initializeCommands();
        initializeConfig();
        getServer().getPluginManager().registerEvents(modEventHandler, this);
    }

    private void initializeCommands() {
        getCommand("test").setExecutor(new Test());
        getCommand("healme").setExecutor(new HealMe());
        getCommand("toggle").setExecutor(new Toggle(this));
    }

    private void initializeConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        consolePrinter.disableMessage();
    }
}
