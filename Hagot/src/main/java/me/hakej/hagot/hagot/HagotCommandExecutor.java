package me.hakej.hagot.hagot;

import me.hakej.hagot.hagot.commands.HagotCommand;
import me.hakej.hagot.hagot.commands.HealMe;
import me.hakej.hagot.hagot.commands.Test;
import me.hakej.hagot.hagot.commands.Toggle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class HagotCommandExecutor implements CommandExecutor {

    private JavaPlugin plugin;

    public HagotCommandExecutor(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Help will be here.");
        } else {
            String commandName = args[0].toLowerCase();
            String[] commandArgs = Arrays.copyOfRange(args, 1, args.length); // Copy args array without first argument
            HagotCommand hagotCommand;
            try {
                switch (commandName) {
                    case "healme":
                        hagotCommand = new HealMe();
                        break;
                    case "test":
                        hagotCommand = new Test();
                        break;
                    case "toggle":
                        hagotCommand = new Toggle(plugin);
                        break;
                    default:
                        throw new IllegalStateException(commandName);
                }
                hagotCommand.execute(sender, command, commandName, commandArgs);
            } catch (IllegalStateException e) {
                ChatColor messageColor = ChatColoring.NEGATIVE;
                String exceptionMessageColored = ChatColoring.INFO + e.getMessage() + messageColor;
                sender.sendMessage(messageColor + "There is no such command as " + exceptionMessageColored + "!");
            }
        }
        return true;
    }
}
