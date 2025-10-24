package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.MainConfig;
import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdSMPT implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(String.format("§aSMPTweaks %s", SMPTweaks.getInstance().getDescription().getVersion()));
            sender.sendMessage("§7Available subcommands: §finfo, help, reload");
            sender.sendMessage("§7Usage: §f/smpt <command>");
            return true;
        }

        String sub = args[0].toLowerCase();

        switch (sub) {
            case "info":
                cmdInfo(sender);
                break;
            case "help":
                cmdHelp(sender);
                break;
            case "reload":
                if (!sender.hasPermission("smpt.reload")) {
                    sender.sendMessage("§cYou don't have permission to use this command.");
                    break;
                }
                cmdReload(sender);
                break;
            default:
                sender.sendMessage("§cUnknown command: " + sub);
                sender.sendMessage("§7Available subcommands: §finfo, help, reload");
                sender.sendMessage("§7Usage: §f/smpt <command>");
        }

        return true;
    }

    public void cmdInfo(CommandSender sender) {
        sender.sendMessage("§a= = = SMPTweaks = = =");
        sender.sendMessage(String.format("§7%s", SMPTweaks.getInstance().getDescription().getDescription()));
        sender.sendMessage(String.format("§7Version: §f%s", SMPTweaks.getInstance().getDescription().getVersion()));
        sender.sendMessage(String.format("§7Authors: §f%s", SMPTweaks.getInstance().getDescription().getAuthors()));
        sender.sendMessage(String.format("§7Website: §f%s", SMPTweaks.getInstance().getDescription().getWebsite()));
    }

    public void cmdHelp(CommandSender sender) {
        sender.sendMessage("§6SMPT command overview:");
        sender.sendMessage("§7>> §e/smpt §7- §fShow info and help");
        sender.sendMessage("§7>> §e/message, /msg §7- §fSend a player a private message");
        sender.sendMessage("§7>> §e/reply, /r §7- §fReply to the last private message");
        sender.sendMessage("§7>> §e/discord §7- §fShow discord invite link");
        sender.sendMessage("§7>> §e/playerinfo §7- §fShow player related info from invite register");
        sender.sendMessage("§7>> §e/trace §7- §fView the invite trace that led to the invitation of a player");
        sender.sendMessage("§7>> §e/invite §7- §fEnable a player to join the server (whitelist)");
        sender.sendMessage("§7>> §e/suspend §7- §fRevoke a player's access to the server (ban)");
        sender.sendMessage("§7>> §e/reinstate §7- §fRestore a player's access to the server (unban)");
        sender.sendMessage("§7>> §e/purge §7- §fEntirely remove a player's record from the invite log");
        sender.sendMessage("§7>> §e/motd §7- §fChange the MOTD visible in the server browser");
        sender.sendMessage("§7>> §e/lock §7- §fEnable maintenance mode. Players may no longer log in");
        sender.sendMessage("§7>> §e/unlock §7- §fDisable maintenance mode. Player access is restored");
    }

    public void cmdReload(CommandSender sender) {
        MainConfig.getInstance().load();
        sender.sendMessage("§aReloaded main config file.");
    }
}
