package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.TimeFormatter;
import net.prismarray.smptweaks.InviteLog;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class CmdPlayerInfo implements CommandExecutor {

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

        sender.sendMessage(String.format("§6Known information about §e§l%s§6:", player.getName()));
        try {
            sender.sendMessage("§6>> §7Invited by: §f" + Bukkit.getOfflinePlayer(InviteLog.getInviteID(playerID)).getName());
        } catch (Exception e) {
            sender.sendMessage("§6>> §7Invited by §fa server admin");
        }
        sender.sendMessage("§6>> §7Invited on: §f" + TimeFormatter.toDate(InviteLog.getInviteTime(playerID)));
        sender.sendMessage("§6>> §7Status: " + (InviteLog.isSuspended(playerID) ? "§c§lSUSPENDED" : "§a§lACTIVE"));


        if (InviteLog.isSuspended(playerID)) {
            try {
                sender.sendMessage("§6>> §7Suspended by: §f" + Bukkit.getOfflinePlayer(InviteLog.getSuspenderID(playerID)).getName());
            } catch (Exception e) {
                sender.sendMessage("§6>> §7Suspended by §fa server admin");
            }
            sender.sendMessage("§6>> §7Suspended on: §f" + TimeFormatter.toDate(InviteLog.getSuspendTime(playerID)));
        }

        return true;
    }
}
