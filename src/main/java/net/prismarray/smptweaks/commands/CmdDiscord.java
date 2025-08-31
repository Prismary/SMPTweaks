package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.MainConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdDiscord implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(MainConfig.getDiscord()[0]);
        sender.sendMessage(MainConfig.getDiscord()[1]);
        return true;
    }
}
