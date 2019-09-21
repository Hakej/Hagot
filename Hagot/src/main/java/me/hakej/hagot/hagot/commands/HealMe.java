package me.hakej.hagot.hagot.commands;

import me.hakej.hagot.hagot.utils.ChatColoring;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealMe implements CommandExecutor {

    private final static double MAX_PLAYER_HEALTH = 20.0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColoring.NEGATIVE + "Only players can execute this command!");
        } else {
            Player player = (Player) sender;
            if (args.length != 1) {
                player.sendMessage(ChatColoring.NEGATIVE + "Wrong usage of the command. Correct usage: " +
                        ChatColoring.MOD + "/hg " +
                        ChatColoring.MARKED + label +
                        ChatColoring.INFO + " <heal-points>");
            } else {
                try {
                    double playerHealth = player.getHealth();
                    double addHealth = Double.parseDouble(args[0]);
                    if (playerHealth >= MAX_PLAYER_HEALTH) {
                        player.sendMessage(ChatColoring.WARN + "You have max health.");
                    } else {
                        ChatColor messageColor = ChatColoring.POSITIVE;
                        double newHealth = playerHealth + addHealth;
                        player.setHealth(Math.min(newHealth, MAX_PLAYER_HEALTH));
                        double healedHealth = Math.floor((player.getHealth() - playerHealth) * 1e2) / 1e2;

                        player.sendMessage(messageColor + "You have been healed for " +
                                ChatColoring.INFO + healedHealth +
                                messageColor + " health.");
                    }
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColoring.NEGATIVE + "Please input a real number!");
                }
            }
        }
        return true;
    }
}
