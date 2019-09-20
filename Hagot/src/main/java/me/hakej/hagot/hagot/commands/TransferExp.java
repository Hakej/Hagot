package me.hakej.hagot.hagot.commands;

import me.hakej.hagot.hagot.ChatColoring;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TransferExp implements HagotCommand {

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColoring.NEGATIVE + "Only players can execute this command!");
        } else {
            if (args.length != 2) {
                sender.sendMessage(ChatColoring.NEGATIVE + "Wrong usage of the command. Correct usage: " +
                        ChatColoring.MOD + "/hg transferexp <target> <exp-points>");
            } else {
                String targetName = args[0];
                Player target = sender.getServer().getPlayer(targetName);
                if (target == null) {
                    ChatColor messageColor = ChatColoring.NEGATIVE;
                    sender.sendMessage(messageColor + "There is no such player as " +
                            ChatColoring.INFO + targetName + messageColor + "!");
                } else {
                    Player grantor = (Player) sender;
                    int grantorExp = grantor.getTotalExperience();
                    int expToTransfer = Integer.parseInt(args[0]);
                    if (expToTransfer > grantorExp) {
                        grantor.sendMessage(ChatColoring.NEGATIVE + "You don't have enough exp to transfer! " +
                                "(You have: " + ChatColoring.INFO + grantorExp + ChatColoring.RESET + ".");
                    } else {
                        int targetExp = target.getTotalExperience();
                        int newGrantorExp = grantorExp - expToTransfer;
                        int newTargetExp = targetExp + expToTransfer;
                        grantor.setExp(newGrantorExp);
                        target.setExp(newTargetExp);
                        ChatColor messageColor = ChatColoring.POSITIVE;
                        grantor.sendMessage(messageColor + "You have sent successfully transfered [" +
                                ChatColoring.INFO + expToTransfer + messageColor + "] exp to " +
                                ChatColoring.MARKED + target.getName());
                        target.sendMessage(messageColor + "You have received [" +
                                ChatColoring.INFO + expToTransfer + messageColor + "] exp from "
                                + ChatColoring.MARKED + grantor.getName());
                    }
                }
            }
        }
        return true;
    }
}
