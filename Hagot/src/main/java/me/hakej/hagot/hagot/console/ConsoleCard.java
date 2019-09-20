package me.hakej.hagot.hagot.console;

import me.hakej.hagot.hagot.ChatColoring;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ConsoleCard {

    private static final int CARD_MARGIN_LEFT = 3;
    private static final int CARD_MARGIN_RIGHT = CARD_MARGIN_LEFT;
    private static final char CARD_BORDER_SYMBOL = '#';
    private static final boolean CARD_ROW_CENTERED = true;

    public static void sendCard(JavaPlugin plugin) {
        String name = plugin.getName();
        String version = plugin.getDescription().getVersion();
        String description = plugin.getDescription().getDescription();

        String coloredName = ChatColoring.MOD + name + ChatColoring.RESET;
        String coloredVersion = ChatColoring.MARKED + version + ChatColoring.RESET;
        String coloredDescription = ChatColoring.INFO + description + ChatColoring.RESET;

        List<String> cardContent = new ArrayList<>();
        cardContent.add(coloredName);
        cardContent.add(coloredVersion);
        cardContent.add(coloredDescription);

        ConsoleCommandSender console = plugin.getServer().getConsoleSender();
        int cardWidth = calculateCardWidth(cardContent);

        sendCardBorder(console, cardWidth, true);
        sendCardContent(console, cardWidth, cardContent);
        sendCardBorder(console, cardWidth, false);
    }

    private static int calculateCardWidth(List<String> cardContent) {
        int cardWidth = 0;

        for (String row : cardContent) {
            int length = row.length();
            if (length > cardWidth) {
                cardWidth = length - countColorCharsInString(row);
            }
        }
        return cardWidth + CARD_MARGIN_LEFT + CARD_MARGIN_RIGHT;
    }

    // Count how many characters in String define its color
    // We need to know amount of chars that define color of the content for card adjustments,
    // because they're inside the String, but not shown in the console.
    private static int countColorCharsInString(String string) {
        int colorCharsInStringAmount = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '§') {
                colorCharsInStringAmount += 2;
            }
        }
        return colorCharsInStringAmount;
    }

    private static void sendCardBorder(ConsoleCommandSender console, int width, boolean isBorderStart) {
        String border = generateCardRowFilled(CARD_BORDER_SYMBOL, width);
        String margin = generateCardRowFilled(' ', width);

        if (isBorderStart) {
            console.sendMessage(border);
            console.sendMessage(margin);
        } else {
            console.sendMessage(margin);
            console.sendMessage(border);
        }
    }

    private static void sendCardContent(ConsoleCommandSender console, int width, List<String> content) {
        for (String row : content) {
            console.sendMessage(generateCardRow(row, width));
        }
    }

    // Generate String, starting and ending with CARD_SYMBOL, filled with character passed as an argument
    private static String generateCardRowFilled(char character, int width) {
        StringBuilder cardRow = new StringBuilder();

        cardRow.append(CARD_BORDER_SYMBOL);
        while (cardRow.length() <= width) {
            cardRow.append(character);
        }
        cardRow.append(CARD_BORDER_SYMBOL);
        return cardRow.toString();
    }

    // Generate String, starting and ending with CARD_SYMBOL with String inside passed as an argument
    // Also include a margin to the left off the content
    private static String generateCardRow(String content, int width) {
        StringBuilder cardRow = new StringBuilder();
        int colorCharsAmount = countColorCharsInString(content);

        cardRow.append(CARD_BORDER_SYMBOL);
        for (int i = 0; i < CARD_MARGIN_LEFT; i++) {
            cardRow.append(' ');
        }

        if (CARD_ROW_CENTERED) {
            int widthMiddle = width / 2;
            int contentMiddle = content.length() / 2;
            int length;
            int middle;
            while ((length = cardRow.length() - colorCharsAmount) <= width) {
                middle = widthMiddle - contentMiddle;
                if (length + 1 == middle) {
                    cardRow.append(content);
                } else {
                    cardRow.append(' ');
                }
            }
        } else {
            cardRow.append(content);
            while (cardRow.length() - colorCharsAmount <= width) {
                cardRow.append(' ');
            }
        }
        cardRow.append(CARD_BORDER_SYMBOL);
        return cardRow.toString();
    }
}
