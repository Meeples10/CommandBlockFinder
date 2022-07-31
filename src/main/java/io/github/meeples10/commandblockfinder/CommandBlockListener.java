package io.github.meeples10.commandblockfinder;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandBlockListener implements Listener {
    @EventHandler
    public void onCommand(ServerCommandEvent e) {
        if(e.getSender() instanceof BlockCommandSender) {
            BlockCommandSender s = (BlockCommandSender) e.getSender();
            int current = Main.LOG.getOrDefault(s.getBlock().getLocation(), 0);
            Main.LOG.put(s.getBlock().getLocation(), current + 1);
        }
    }
}
