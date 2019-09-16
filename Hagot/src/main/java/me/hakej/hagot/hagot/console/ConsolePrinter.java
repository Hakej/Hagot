package me.hakej.hagot.hagot;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ConsolePrinter {

    private JavaPlugin plugin;
    private String pluginName;
    private Logger logger;

    public ConsolePrinter(JavaPlugin plugin) {
        this.plugin = plugin;
        this.pluginName = plugin.getName();
        this.logger = plugin.getLogger();
    }

    public void enableMessage() {
        ConsoleCard.sendCard(plugin);
        logger.info(pluginName + " enabled.");
    }

    public void disableMessage() {
        logger.info(pluginName + " disabled.");
    }
}
