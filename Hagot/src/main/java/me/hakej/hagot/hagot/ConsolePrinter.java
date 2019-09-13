package me.hakej.hagot.hagot;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ConsolePrinter {

    private static final int START_CONSOLE_MESSAGE_WIDTH = 61;
    private static final char START_CONSOLE_MESSAGE_SYMBOL = '=';

    private ConsoleCommandSender consoleCommandSender;
    private JavaPlugin javaPlugin;

    public ConsolePrinter(ConsoleCommandSender consoleCommandSender, JavaPlugin javaPlugin) {
        this.consoleCommandSender = consoleCommandSender;
        this.javaPlugin = javaPlugin;
    }

    public void sendStartConsoleMessage() {
        String pluginName = javaPlugin.getName();
        String pluginVersion = javaPlugin.getDescription().getVersion();
        String pluginDescription = javaPlugin.getDescription().getDescription();

        consoleCommandSender.sendMessage(messageLine());
        consoleCommandSender.sendMessage(messageContent(" "));
        consoleCommandSender.sendMessage(messageContent(pluginName));
        consoleCommandSender.sendMessage(messageContent(pluginVersion));
        consoleCommandSender.sendMessage(messageContent(pluginDescription));
        consoleCommandSender.sendMessage(messageContent(" "));
        consoleCommandSender.sendMessage(messageLine());
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
}
