package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.ReplyManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdReply implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command may only be used by players.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUsage: §f/r <message>");
            return true;
        }

        Player player = Bukkit.getPlayerExact(sender.getName());
        Player target = ReplyManager.getTarget(player);

        if (target == null || !target.isOnline()) {
            player.sendMessage("§cFound nobody to reply to.");
            return true;
        }

        if (player == target) {
            player.sendMessage("§cThe recipient must be someone else!");
            return true;
        }

        String message = String.join(" ", args);

        player.sendMessage("§6[§eYou §6→ §e" + target.getName() + "§6] §f" + message);
        target.sendMessage("§6[§e" + player.getName() + " §6→ §eYou§6] §f" + message);

        ReplyManager.connect(player, target);

        return true;
    }
}
