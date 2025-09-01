package net.prismarray.smptweaks;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Tab {

    public static void setPlayerTab(Player player, String headerText, String footerText) {
        Component header = Component.text(headerText);
        Component footer = Component.text(footerText);
        player.sendPlayerListHeaderAndFooter(header, footer);
    }

    public static void setGlobalTab(String headerText, String footerText) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            setPlayerTab(player, headerText, footerText);
        }
    }

}
