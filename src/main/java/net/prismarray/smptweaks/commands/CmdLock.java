package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdLock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (SMPTweaks.getInstance().isLocked()) {
            sender.sendMessage("§aThe server has been re-opened!");
        } else {
            sender.sendMessage("§cThe server has been locked!");
        }

        SMPTweaks.getInstance().toggleLock();

        return true;
    }
}
