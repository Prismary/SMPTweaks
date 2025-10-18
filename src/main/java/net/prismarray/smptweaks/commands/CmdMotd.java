package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.MainConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdMotd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (MainConfig.isMOTDEnabled()) {
                sender.sendMessage(String.format("§6The MOTD mode is currently set to §e%s§6.", MainConfig.isMOTDRandom() ? "random" : "static"));
                sender.sendMessage("§7Available subcommands: §fread, set, add, mode");
                sender.sendMessage("§7Usage: §f/motd <command>");
            } else {
                sender.sendMessage("§6The custom MOTD feature is currently §cdisabled§6.");
            }
            return true;
        }

        String sub = args[0].toLowerCase();

        switch (sub) {
            case "read":
                cmdRead(sender);
                break;
            case "set":
                cmdSet(sender, args);
                break;
            case "add":
                cmdAdd(sender, args);
                break;
            case "mode":
                cmdMode(sender, args);
                break;
            default:
                sender.sendMessage("§cUnknown command: " + sub);
                sender.sendMessage("§7Available subcommands: §fread, set, add, mode");
                sender.sendMessage("§7Usage: §f/motd <command>");
        }

        return true;
    }

    public String parseNewMOTD(String[] args) { // ignores first arg as sub command!
        String message = "";
        for (int i = 1; i < args.length; i++) {
            message += args[i] + " ";
        }

        return message.substring(0, message.length() - 1);
    }

    public void cmdRead(CommandSender sender) {
        sender.sendMessage(String.format("§6The static MOTD message currently reads: §r\n%s", MainConfig.getStaticMOTD()[2]));
    }

    public void cmdSet(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage("§cPlease specify the new static message.");
            sender.sendMessage("§7Usage: §f/motd set <message>");
            return;
        }

        String message = parseNewMOTD(args);
        MainConfig.setStaticMessage(message);
        sender.sendMessage(String.format("§aSet static MOTD message to: §r\n%s", message));
    }

    public void cmdAdd(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage("§cPlease specify the new random message.");
            sender.sendMessage("§7Usage: §f/motd add <message>");
            return;
        }

        String message = parseNewMOTD(args);
        MainConfig.addRandomMessage(message);
        sender.sendMessage(String.format("§aAdded new random MOTD: §r\n%s", message));
    }

    public void cmdMode(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage("§cPlease specify a mode: 'random' or 'static'" + args[1].toLowerCase());
            sender.sendMessage("§7Usage: §f/motd mode <mode>");
            sender.sendMessage("§7To view the current mode: §f/motd");
            return;
        }

        switch (args[1].toLowerCase()) {
            case "random":
                MainConfig.setMOTDRandomMode(true);
                sender.sendMessage("§6MOTD mode is now §erandom§6!");
                break;
            case "static":
                MainConfig.setMOTDRandomMode(false);
                sender.sendMessage("§6MOTD mode is now §estatic§6!");
                break;
            default:
                sender.sendMessage("§cUnknown mode: " + args[1].toLowerCase());
                sender.sendMessage("§7Mode must be either 'random' or 'static'");
                sender.sendMessage("§7Usage: §f/motd mode <mode>");
        }
    }
}
