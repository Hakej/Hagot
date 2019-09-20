package me.hakej.hagot.hagot;

import me.hakej.hagot.hagot.console.ConsolePrinter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hagot extends JavaPlugin {

    private ConsolePrinter consolePrinter = new ConsolePrinter(this);
    private ModEventHandler modEventHandler = new ModEventHandler(this);
    private HagotCommandExecutor hagotCommandExecutor = new HagotCommandExecutor(this);

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
        getCommand("hg").setExecutor(hagotCommandExecutor);
        getCommand("hagot").setExecutor(hagotCommandExecutor);
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
