package me.hakej.hagot.hagot.commands;

import me.hakej.hagot.hagot.ChatColoring;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player != null) {
            player.sendMessage(ChatColoring.TEST + "Test command works!!");
        }
        return true;
    }
}
