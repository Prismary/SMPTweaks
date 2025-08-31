package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.MainConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdMotd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(String.format("§7The MOTD appendix currently reads §9\"%s§9\"§7.", MainConfig.getMOTD()[2]));
            return true;
        }

        String appendix = "";
        for (String arg : args) {
            appendix += arg;
            appendix += " ";
        }

        appendix.substring(0, appendix.length() - 1);

        MainConfig.setAppendix(appendix);
        sender.sendMessage(String.format("§aSet MOTD appendix to §9\"%s§9\"§a!", appendix));
        return true;
    }
}
