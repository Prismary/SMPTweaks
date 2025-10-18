package net.prismarray.smptweaks.commands;

import net.kyori.adventure.text.Component;
import net.prismarray.smptweaks.MainConfig;
import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdLock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (MainConfig.isLocked()) {
            sender.sendMessage("§cThe server is already locked!");
            return true;
        }

        MainConfig.setLock(true);
        sender.sendMessage("§2The server has been locked! §aKicking all online players...");

        int count = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("smpt.lockbypass") && !player.hasPermission("smpt.lock")) {
                player.kick(Component.text("§cThe server has been temporarily closed for maintenance."));
                count++;
            }
        }

        sender.sendMessage("§aSuccessfully kicked §2" + count + " §aplayers.");

        return true;
    }
}
