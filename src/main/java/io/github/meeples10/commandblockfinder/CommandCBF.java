package io.github.meeples10.commandblockfinder;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import io.github.meeples10.meepcore.MCommand;
import io.github.meeples10.meepcore.Messages;

public class CommandCBF extends MCommand {
    private static final int PAGE_SIZE = 10;

    public CommandCBF(String usageKey) {
        super(usageKey);
    }

    @Override
    public boolean run(CommandSender sender, Command command, String label, String[] args, String locale) {
        if(sender.hasPermission("commandblockfinder.use")) {
            if(args.length > 0) {
                if(args[0].equalsIgnoreCase("start")) {
                    Main.active = true;
                    sender.sendMessage(Messages.translate(locale, "command.commandblockfinder.start"));
                } else if(args[0].equalsIgnoreCase("show")) {
                    if(Main.LOG.isEmpty()) {
                        sender.sendMessage(Messages.translate(locale, "command.commandblockfinder.show.empty"));
                        return true;
                    }
                    Map<Location, Integer> map = Main.sortByValue(Main.LOG);
                    StringBuilder sb = new StringBuilder();
                    int page = 0;
                    if(args.length > 1) {
                        try {
                            page = Integer.valueOf(args[1]) - 1;
                        } catch(NumberFormatException e) {
                            sender.sendMessage(Messages.invalidArgument(locale, args[1]));
                            return true;
                        }
                    }
                    if(page < 0) {
                        sender.sendMessage(Messages.invalidArgument(locale, args[1]));
                        return true;
                    }
                    int i = 0;
                    int start = PAGE_SIZE * page;
                    int end = PAGE_SIZE * page + 10;
                    for(Location l : map.keySet()) {
                        if(i >= start && i < end) {
                            sb.append(Messages.format("$t[$w%s$t: $hl%d$t, $hl%d$t, $hl%d$t] %s",
                                    l.getWorld().getName(), l.getBlockX(), l.getBlockY(), l.getBlockZ(),
                                    Messages.translate(locale,
                                            map.get(l) == 1 ? "command.commandblockfinder.show.singular"
                                                    : "command.commandblockfinder.show.plural",
                                            map.get(l))));
                            if(i < end - 1) sb.append("\n");
                        }
                        i++;
                    }
                    sender.sendMessage(
                            Messages.section(Messages.translate(locale, "command.commandblockfinder.show.header",
                                    (page / 10) + 1, (map.size() / 10) + 1), sb.toString()));
                } else if(args[0].equalsIgnoreCase("stop")) {
                    Main.active = false;
                    sender.sendMessage(Messages.translate(locale, "command.commandblockfinder.stop"));
                } else if(args[0].equalsIgnoreCase("clear")) {
                    Main.LOG.clear();
                    sender.sendMessage(Messages.translate(locale, "command.commandblockfinder.clear"));
                } else {
                    return false;
                }
            } else {
                sender.sendMessage(Messages.section(Main.NAME, "$hl/cbf start$t: "
                        + Messages.translate(locale, "command.commandblockfinder.help.start") + "$hl\n" + "/cbf show ["
                        + Messages.translate(locale, "command.commandblockfinder.help.page") + "]$t: "
                        + Messages.translate(locale, "command.commandblockfinder.help.show") + "$hl\n" + "/cbf stop$t: "
                        + Messages.translate(locale, "command.commandblockfinder.help.stop") + "$hl\n"
                        + "/cbf clear$t: " + Messages.translate(locale, "command.commandblockfinder.help.clear")));
            }
        } else {
            sender.sendMessage(Messages.noPermissionMessage(locale));
        }
        return true;
    }
}
