package io.github.meeples10.commandblockfinder;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandBlockListener implements Listener {
    @EventHandler
    public void onCommand(ServerCommandEvent e) {
        if(!Main.active) return;
        if(e.getSender() instanceof BlockCommandSender) {
            BlockCommandSender s = (BlockCommandSender) e.getSender();
            Main.LOG.put(s.getBlock().getLocation(), Main.LOG.getOrDefault(s.getBlock().getLocation(), 0) + 1);
        }
    }
}
