package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.ReplyManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdMessage implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command may only be used by players.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("§cUsage: §f/msg <name> <message>");
            return true;
        }

        Player player = Bukkit.getPlayerExact(sender.getName());
        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null || !target.isOnline()) {
            player.sendMessage("§cRecipient not found.");
            return true;
        }

        if (player == target) {
            player.sendMessage("§cThe recipient must be someone else!");
            return true;
        }

        String message = String.join(" ", args).substring(args[0].length()).trim();

        player.sendMessage("§6[§eYou §6→ §e" + target.getName() + "§6] §f" + message);
        target.sendMessage("§6[§e" + player.getName() + " §6→ §eYou§6] §f" + message);

        ReplyManager.connect(player, target);

        return true;
    }
}
