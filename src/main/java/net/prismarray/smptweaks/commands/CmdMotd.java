package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.MainConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdMotd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO subdivide into read, set, add

        if (args.length == 0) {
            sender.sendMessage(String.format("§7The static MOTD message currently reads §9\"%s§9\"§7.", MainConfig.getStaticMOTD()[2]));
            return true;
        }

        String message = "";
        for (String arg : args) {
            message += arg;
            message += " ";
        }

        message.substring(0, message.length() - 1);

        MainConfig.setStaticMessage(message);
        sender.sendMessage(String.format("§aSet static MOTD message to §9\"%s§9\"§a!", message));
        return true;
    }
}
