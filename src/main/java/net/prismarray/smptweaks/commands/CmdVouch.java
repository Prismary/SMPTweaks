package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.VouchLog;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class CmdVouch implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }

        UUID toRegister = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
        UUID registrar = Bukkit.getPlayer(sender.getName()).getUniqueId();

        if (VouchLog.registerPlayer(toRegister, registrar)) {
            sender.sendMessage(String.format("§aVouched for §2%s §ato play on the server!", args[0]));
        } else {
            sender.sendMessage(String.format("§cUnable to vouch for §4%s§c! Are they already registered?", args[0]));
        }

        return true;
    }
}
