package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.VouchLog;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CmdSuspend implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }

        UUID toSuspend = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
        UUID suspender;

        try { // getPlayer() will return Null if run by server. Field may remain null in this case
            suspender = Bukkit.getPlayer(sender.getName()).getUniqueId();
        } catch (NullPointerException e) {
            suspender = null;
        }

        Player onlinePlayer = Bukkit.getPlayer(toSuspend);

        if (VouchLog.suspendPlayer(toSuspend, suspender)) {
            if (onlinePlayer != null) {
                onlinePlayer.kickPlayer("§cYou have been suspended from the server!");
            }

            sender.sendMessage(String.format("§4Suspended §c%s §4from the server.", args[0]));
        } else {
            sender.sendMessage(String.format("§cUnable to suspend §4%s! §cAre they not registered or already suspended?", args[0]));
        }

        return true;
    }
}
