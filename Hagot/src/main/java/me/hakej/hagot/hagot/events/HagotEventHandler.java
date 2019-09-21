package me.hakej.hagot.hagot.events;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.plugin.java.JavaPlugin;

public interface SpigotEventHandler {

    static <T> void handle(T t) {
        throw new NotImplementedException();
    }

    static <T> void handle(JavaPlugin plugin, T t) {
        throw new NotImplementedException();
    }
}
