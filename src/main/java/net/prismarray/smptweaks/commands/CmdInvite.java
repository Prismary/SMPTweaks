package net.prismarray.smptweaks.commands;

import net.prismarray.smptweaks.MainConfig;
import net.prismarray.smptweaks.SMPTweaks;
import net.prismarray.smptweaks.UsernameCheck;
import net.prismarray.smptweaks.InviteLog;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.eclipse.sisu.launch.Main;

import java.util.UUID;

public class CmdInvite implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cPlease specify a username!");
            return true;
        }

        if (MainConfig.useInviteCooldown() && sender instanceof Player && !isCooldownExpired(Bukkit.getPlayer(sender.getName()))) {
            sender.sendMessage("§cYou must have been registered for at least one day before inviting new players!");
            return true;
        }

        Bukkit.getScheduler().runTaskAsynchronously(SMPTweaks.getInstance(), () -> {
            // check whether username exists
            boolean exists = UsernameCheck.isValidUsername(args[0]);

            // return to main thread for remaining logic
            Bukkit.getScheduler().runTask(SMPTweaks.getInstance(), () -> {
                if (!exists) { // Return early if username does not exist
                    sender.sendMessage(String.format("§4%s§c is not a valid username!", args[0]));
                    return;
                }

                UUID toRegister;
                UUID registrar;

                toRegister = Bukkit.getOfflinePlayer(args[0]).getUniqueId();

                if (InviteLog.isRegistered(toRegister)) { // return early if player is already registered
                    sender.sendMessage(String.format("§4%s§c is already registered!", args[0]));
                    return;
                }

                try { // getPlayer() will return Null if run by server. Field may remain null in this case
                    registrar = Bukkit.getPlayer(sender.getName()).getUniqueId();
                } catch (NullPointerException e) {
                    registrar = null;
                }

                // execute registration method
                if (InviteLog.registerPlayer(args[0], toRegister, registrar)) {
                    sender.sendMessage(String.format("§aInvited §2%s §ato play on the server!", args[0]));
                } else {
                    sender.sendMessage(String.format("§cUnable to invite §4%s§c! An error occurred.", args[0]));
                }
            });
        });

        return true;
    }

    public boolean isCooldownExpired(Player player) {
        return System.currentTimeMillis() - Long.parseLong(InviteLog.getInviteTime(player.getUniqueId())) > 86400000 ||
                player.hasPermission("smpt.cooldownbypass"); // 24h cooldown
    }
}
