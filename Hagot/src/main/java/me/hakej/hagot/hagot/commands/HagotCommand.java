package me.hakej.hagot.hagot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface HagotCommand {

    boolean execute(CommandSender sender, Command command, String label, String[] args);
}
