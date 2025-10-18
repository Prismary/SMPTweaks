package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.InviteLog;
import net.prismarray.smptweaks.MainConfig;
import net.prismarray.smptweaks.TimeFormatter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CmdTrace implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cPlease specify a username!");
            return true;
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        UUID playerID = player.getUniqueId();

        if (!InviteLog.isRegistered(playerID)) {
            sender.sendMessage("§cThe specified user is not registered!");
            return true;
        }

        List<UUID> trace = getUUIDTrace(playerID);

        sender.sendMessage(String.format("\n§6Invite trace of §e§l%s§6:", player.getName()));
        // Print invite trace minus root player
        for (int i = 0; i < trace.size()-1; i++) {
            sender.sendMessage(String.format("§6>> §f%s §7invited by §f%s §7on §f%s",
                    Bukkit.getOfflinePlayer(trace.get(i)).getName(),
                    Bukkit.getOfflinePlayer(trace.get(i+1)).getName(),
                    TimeFormatter.toDate(InviteLog.getInviteTime(trace.get(i)))
            ));
        }
        // Root player
        sender.sendMessage(String.format("§6>> §f%s §7registered on §f%s",
                Bukkit.getOfflinePlayer(trace.getLast()).getName(),
                TimeFormatter.toDate(InviteLog.getInviteTime(trace.getLast()))
        ));

        sender.sendMessage(String.format("\nTo view the simplified info, use §b/playerinfo %s", player.getName()));

        return true;
    }

    public List<UUID> getUUIDTrace(UUID playerID) {
        List<UUID> trace = new ArrayList<>();

        UUID current = playerID;
        int hops = 0;
        while (current != null || hops > 20) {
            trace.add(current);
            current = InviteLog.getInviteID(current);
            hops++;
        }

        return trace;
    }
}
