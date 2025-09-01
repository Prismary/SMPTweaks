package net.prismarray.smptweaks.events;

import net.prismarray.smptweaks.InviteLog;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.UUID;

public class EvtPlayerLogin extends EventBase {

    @EventHandler
    public void playerLogin(PlayerLoginEvent event) {
        UUID playerID = event.getPlayer().getUniqueId();

        if (!InviteLog.isRegistered(playerID)) {
            disallow(event, "§cYou are not registered to play on this server.");
            return;
        }

        if (InviteLog.isSuspended(playerID)) {
            disallow(event, "§cYou have been suspended from this server!");
            return;
        }
    }

    public void disallow(PlayerLoginEvent event, String msg) {
        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, msg);
    }
}
