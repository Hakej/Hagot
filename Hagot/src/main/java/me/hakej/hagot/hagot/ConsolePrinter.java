package me.hakej.hagot.hagot;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ConsolePrinter {

    private static final int START_CONSOLE_MESSAGE_WIDTH = 61;
    private static final char START_CONSOLE_MESSAGE_SYMBOL = '=';

    private ConsoleCommandSender console;
    private JavaPlugin plugin;

    public ConsolePrinter(ConsoleCommandSender console, JavaPlugin plugin) {
        this.console = console;
        this.plugin = plugin;
    }

    public void enableMessage() {
        String name = plugin.getName();
        String version = plugin.getDescription().getVersion();
        String description = plugin.getDescription().getDescription();

        console.sendMessage(messageLine());
        console.sendMessage(messageContent(" "));
        console.sendMessage(messageContent(name));
        console.sendMessage(messageContent(version));
        console.sendMessage(messageContent(description));
        console.sendMessage(messageContent(" "));
        console.sendMessage(messageLine());
    }

    private String messageLine() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < START_CONSOLE_MESSAGE_WIDTH; i++) {
            stringBuilder.append(ConsolePrinter.START_CONSOLE_MESSAGE_SYMBOL);
        }
        return stringBuilder.toString();
    }

    private String messageContent(String content) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(START_CONSOLE_MESSAGE_SYMBOL);
        while (stringBuilder.length() < START_CONSOLE_MESSAGE_WIDTH - 1) {
            int widthMiddle = START_CONSOLE_MESSAGE_WIDTH / 2;
            int contentMiddle = content.length() / 2;
            if (stringBuilder.length() == (widthMiddle - contentMiddle)) {
                stringBuilder.append(content);
            } else {
                stringBuilder.append(' ');
            }
        }
        stringBuilder.append(START_CONSOLE_MESSAGE_SYMBOL);
        return stringBuilder.toString();
    }

    public void disableMessage() {
        console.sendMessage(ChatColor.AQUA + plugin.getName() +
                ChatColor.RESET + " disabled.");
    }
}
