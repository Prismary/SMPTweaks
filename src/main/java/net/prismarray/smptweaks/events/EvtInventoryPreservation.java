package net.prismarray.smptweaks.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

public class EvtInventoryPreservation extends EventBase {

    @EventHandler
    public void inventoryPreservation(PlayerDeathEvent event) {

        if (!consumeTotem(event.getEntity())) {
            return;
        }

        event.setKeepInventory(true);
        event.setKeepLevel(true);
        event.getDrops().clear();
        event.setDroppedExp(0);
    }

    private boolean consumeTotem(Player player) {
        player.updateInventory();
        PlayerInventory inventory = player.getInventory();

        HashMap<Integer, ? extends ItemStack> totemCandidates = inventory.all(Material.ECHO_SHARD);
        for (ItemStack item : totemCandidates.values()) {
            if (item.getItemMeta().getDisplayName().equals("§aTotem of Preservation")) {
                item.setAmount(item.getAmount() - 1);
                return true;
            }
        }

        return false;
    }
}
