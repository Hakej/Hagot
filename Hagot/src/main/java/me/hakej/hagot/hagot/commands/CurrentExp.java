package me.hakej.hagot.hagot.commands;

import me.hakej.hagot.hagot.utils.ChatColoring;
import me.hakej.hagot.hagot.utils.ExperienceModifier;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CurrentExp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColoring.NEGATIVE + "Only players can execute this");
        } else {
            int experience = ExperienceModifier.getPlayerExp(((Player) sender));
            ChatColor messageColor = ChatColoring.POSITIVE;
            sender.sendMessage(messageColor + "You have " +
                    ChatColoring.INFO + experience +
                    messageColor + " experience points.");
        }
        return true;
    }
}
