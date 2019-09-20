package me.hakej.hagot.hagot.commands;

import me.hakej.hagot.hagot.ChatColoring;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Toggle implements HagotCommand {

    private final JavaPlugin plugin;

    public Toggle(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();
        if (args.length < 1) {
            sender.sendMessage(ChatColoring.NEGATIVE + "Please include which event to toggle!");
        } else {
            String eventToToggle = args[0];
            String path = "event-toggle." + eventToToggle;
            boolean state = config.getBoolean(path);
            if (eventToToggle == null) {
                sender.sendMessage("ERROR");
            } else {
                String message = "Event " + eventToToggle + " is now ";
                state = !state;
                config.set(path, state);
                plugin.saveConfig();
                if (state) {
                    message += ChatColoring.POSITIVE + "ON";
                } else {
                    message += ChatColoring.NEGATIVE + "OFF";
                }
                message += ChatColoring.RESET + ".";
                sender.sendMessage(message);
            }
        }
        return true;
    }
}
