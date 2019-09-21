package me.hakej.hagot.hagot.commands;

import me.hakej.hagot.hagot.utils.ChatColoring;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColoring.TEST + "Test command works!!");
        return true;
    }
}
