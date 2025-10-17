package net.prismarray.smptweaks.commands;

import net.kyori.adventure.text.Component;
import net.prismarray.smptweaks.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdUnlock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!MainConfig.isLocked()) {
            sender.sendMessage("§cThe server is already open!");
            return true;
        }

        MainConfig.setLock(false);
        sender.sendMessage("§aThe server has been unlocked!");

        return true;
    }
}
