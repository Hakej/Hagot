package me.hakej.hagot.hagot.commands;

import me.hakej.hagot.hagot.utils.ChatColoring;
import me.hakej.hagot.hagot.utils.ExperienceModifier;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TransferExp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColoring.NEGATIVE + "Only players can execute this command!");
        } else {
            if (args.length != 2) {
                sender.sendMessage(ChatColoring.NEGATIVE + "Wrong usage of the command. Correct usage: " +
                        ChatColoring.MOD + "/hg " +
                        ChatColoring.MARKED + "transferexp " +
                        ChatColoring.TARGET + "<target> " +
                        ChatColoring.INFO + "<exp-points>");
            } else {
                String targetName = args[0];
                Player target = sender.getServer().getPlayer(targetName);
                if (target == null) {
                    ChatColor messageColor = ChatColoring.NEGATIVE;
                    sender.sendMessage(messageColor + "There is no such player as " +
                            ChatColoring.INFO + targetName +
                            messageColor + "!");
                } else {
                    Player grantor = (Player) sender;
                    int expToTransfer = Integer.parseInt(args[1]);
                    int grantorExp = ExperienceModifier.getPlayerExp(grantor);
                    if (expToTransfer > grantorExp) {
                        ChatColor messageColor = ChatColoring.NEGATIVE;
                        grantor.sendMessage(messageColor + "You don't have enough exp to transfer! (You have: " +
                                ChatColoring.INFO + grantorExp +
                                messageColor + ")");
                    } else {
                        ExperienceModifier.givePlayerExp(grantor, -expToTransfer);
                        ExperienceModifier.givePlayerExp(target, expToTransfer);
                        ChatColor messageColor = ChatColoring.POSITIVE;
                        grantor.sendMessage(messageColor + "You have sent successfully transfered " +
                                ChatColoring.INFO + expToTransfer +
                                messageColor + " exp to " +
                                ChatColoring.MARKED + target.getName() +
                                messageColor + ".");
                        target.sendMessage(messageColor + "You have received " +
                                ChatColoring.INFO + expToTransfer +
                                messageColor + " exp from " +
                                ChatColoring.MARKED + grantor.getName() +
                                messageColor + ".");
                    }
                }
            }
        }
        return true;
    }
}
