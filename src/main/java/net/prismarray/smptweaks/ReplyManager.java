package net.prismarray.smptweaks;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ReplyManager {

    private static Map<Player, Player> lastMessageTarget = new HashMap<>();

    public static void connect(Player player1, Player player2) {
        setTarget(player1, player2);
        setTarget(player2, player1);
    }

    public static void setTarget(Player player, Player target) {
        lastMessageTarget.put(player, target);
    }

    public static Player getTarget(Player player) {
        return lastMessageTarget.get(player);
    }

    public static Player purgeEntry(Player player) {
        return lastMessageTarget.remove(player);
    }
}
