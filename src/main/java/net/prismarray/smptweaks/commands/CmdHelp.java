package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.MainConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdHelp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§6§l- SMPT User Commands -");
        sender.sendMessage("\n§e/discord\n§7>> §fGet the invite link to our discord server");
        sender.sendMessage("\n§e/playerinfo\n§7>> §fShows all relevant info about a player (status, who they were invited by, etc.)");
        sender.sendMessage("\n§e/trace\n§7>> §fView the entire invite trace that led to the invitation of a player");
        sender.sendMessage("\n§e/invite\n§7>> §fInvite a player to the server. §cYou will be logged as the inviter and held liable for misbehavior!");

        return true;
    }
}
