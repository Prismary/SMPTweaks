package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.VouchLog;
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

        if (!VouchLog.isRegistered(playerID)) {
            sender.sendMessage("§cThe specified user is not registered!");
            return true;
        }

        sender.sendMessage(String.format("§6Known information about §e§l%s§6:", player.getName()));
        sender.sendMessage("§7Vouched for by: §f" + Bukkit.getOfflinePlayer(VouchLog.getVouchID(playerID)).getName());

        if (VouchLog.isSuspended(playerID)) {
            sender.sendMessage("§7Suspended by: §f" + Bukkit.getOfflinePlayer(VouchLog.getSuspenderID(playerID)).getName());
        }

        return true;
    }
}
