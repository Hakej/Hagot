package me.hakej.hagot.hagot;

import me.hakej.hagot.hagot.commands.*;
import me.hakej.hagot.hagot.utils.ChatColoring;
import me.hakej.hagot.hagot.utils.CommandsNames;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HagotCommandExecutor implements CommandExecutor {

    private JavaPlugin plugin;
    private Map<String, CommandExecutor> commands = new HashMap<>();

    public HagotCommandExecutor(JavaPlugin plugin) {
        this.plugin = plugin;
        initializeCommandsMap();
    }

    private void initializeCommandsMap() {
        commands.put(CommandsNames.CurrentExp, new CurrentExp());
        commands.put(CommandsNames.HealMe, new HealMe());
        commands.put(CommandsNames.Test, new Test());
        commands.put(CommandsNames.Toggle, new Toggle(plugin));
        commands.put(CommandsNames.TransferExp, new TransferExp());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Help will be here.");
        } else {
            String commandName = args[0].toLowerCase();
            if (!commands.containsKey(commandName)) {
                ChatColor messageColor = ChatColoring.NEGATIVE;
                sender.sendMessage(messageColor + "There is no such command as " +
                        ChatColoring.INFO + commandName +
                        messageColor + "!");
            } else {
                // Copy args array without first element
                String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
                commands.get(commandName).onCommand(sender, command, commandName, commandArgs);
            }
        }
        return true;
    }
}
