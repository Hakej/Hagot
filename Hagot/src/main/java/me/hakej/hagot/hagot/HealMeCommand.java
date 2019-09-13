package me.hakej.hagot.hagot;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealMeCommand implements CommandExecutor {

    private final static double MAX_PLAYER_HEALTH = 20;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Please include how much you want to be healed by!");
            } else if (args.length == 1) {
                try {
                    double playerHealth = player.getHealth();
                    double addHealth = Double.parseDouble(args[0]);
                    if (playerHealth >= MAX_PLAYER_HEALTH) {
                        player.sendMessage(ChatColor.YELLOW + "You have max health.");
                    } else {
                        double newHealth = playerHealth + addHealth;
                        player.setHealth(Math.min(newHealth, MAX_PLAYER_HEALTH));
                        player.sendMessage(ChatColor.GRAY + "You have been healed for " + ChatColor.GREEN + Math.floor((player.getHealth() - playerHealth) * 1e2) / 1e2 + " health.");
                    }
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Please input a real number!");
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
        }
        return true;
    }
}
