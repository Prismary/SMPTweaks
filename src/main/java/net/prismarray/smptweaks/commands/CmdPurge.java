package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.InviteLog;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class CmdPurge implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }

        UUID toPurge = Bukkit.getOfflinePlayer(args[0]).getUniqueId();

        if (InviteLog.purgePlayer(toPurge)) {
            sender.sendMessage(String.format("§4%s §chas been removed from the vouch log.", args[0]));
        } else {
            sender.sendMessage(String.format("§cUnable to purge §4%s§c! Are they not registered?", args[0]));
        }

        return true;
    }
}
