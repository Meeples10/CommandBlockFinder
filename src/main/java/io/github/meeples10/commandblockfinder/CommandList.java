package io.github.meeples10.commandblockfinder;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandList implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("commandblockfinder.use")) {
            Map<Location, Integer> map = Main.sortByValue(Main.LOG);
            for(Location l : map.keySet()) {
                sender.sendMessage(String.format("[%s: %d, %d, %d] %d command execution%s", l.getWorld().getName(),
                        l.getBlockX(), l.getBlockY(), l.getBlockZ(), map.get(l), map.get(l) == 1 ? "" : "s"));
            }
        }
        return true;
    }
}
