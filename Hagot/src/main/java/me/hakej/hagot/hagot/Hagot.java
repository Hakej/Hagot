package me.hakej.hagot.hagot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hagot extends JavaPlugin {

    private static final int START_CONSOLE_MESSAGE_WIDTH = 61;
    private static final char START_CONSOLE_MESSAGE_SYMBOL = '=';

    @Override
    public void onEnable() {
        sendStartConsoleMessage();
        initializeCommands();
    }

    private void sendStartConsoleMessage() {
        ConsoleCommandSender consoleSender = getServer().getConsoleSender();
        consoleSender.sendMessage(lineOfSymbols(START_CONSOLE_MESSAGE_SYMBOL));
        consoleSender.sendMessage(lineOfSymbols(' '));
        consoleSender.sendMessage(consoleMessageContent(this.getName()));
        consoleSender.sendMessage(consoleMessageContent(this.getDescription().getVersion()));
        consoleSender.sendMessage(consoleMessageContent(this.getDescription().getDescription()));
        consoleSender.sendMessage(lineOfSymbols(' '));
        consoleSender.sendMessage(lineOfSymbols(START_CONSOLE_MESSAGE_SYMBOL));
    }

    private String lineOfSymbols(char symbol) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(START_CONSOLE_MESSAGE_SYMBOL);
        for (int i = 0; i < START_CONSOLE_MESSAGE_WIDTH - 2; i++) {
            stringBuilder.append(symbol);
        }
        stringBuilder.append(START_CONSOLE_MESSAGE_SYMBOL);
        return stringBuilder.toString();
    }

    private String consoleMessageContent(String content) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(START_CONSOLE_MESSAGE_SYMBOL);
        while (stringBuilder.length() < START_CONSOLE_MESSAGE_WIDTH - 1) {
            if (stringBuilder.length() == ((START_CONSOLE_MESSAGE_WIDTH / 2) - (content.length() / 2))) {
                stringBuilder.append(content);
            } else {
                stringBuilder.append(' ');
            }
        }
        stringBuilder.append(START_CONSOLE_MESSAGE_SYMBOL);
        return stringBuilder.toString();
    }

    private void initializeCommands() {
        this.getCommand("test").setExecutor(this);
        this.getCommand("healme").setExecutor(new CommandClass());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("test")) {
            Player player = Bukkit.getPlayer(sender.getName());
            if (player != null) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Test command works!!");
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + this.getName() + " disabled.");
    }
}
