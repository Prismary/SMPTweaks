package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.VouchLog;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class CmdReinstate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }

        UUID toReinstate = Bukkit.getOfflinePlayer(args[0]).getUniqueId();

        if (VouchLog.reinstatePlayer(toReinstate)) {
            sender.sendMessage(String.format("§2%s §ais no longer suspended.", args[0]));
        } else {
            sender.sendMessage(String.format("§cUnable to reinstate §4%s§c! Are they not registered or not suspended?", args[0]));
        }

        return true;
    }
}
