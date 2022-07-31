package io.github.meeples10.commandblockfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static final String NAME = "CommandBlockFinder";
    public static final Map<Location, Integer> LOG = new HashMap<>();

    @Override
    public void onEnable() {
        this.getCommand("commandblockfinder").setExecutor(new CommandList());
        Bukkit.getPluginManager().registerEvents(new CommandBlockListener(), Bukkit.getPluginManager().getPlugin(NAME));
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for(Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
