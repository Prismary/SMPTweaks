package net.prismarray.smptweaks.events;

import net.prismarray.smptweaks.SMPTweaks;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class EvtNoShulkerboxInEnderchest extends EventBase {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTopInventory().getType() != InventoryType.ENDER_CHEST) return;

        ItemStack item = null;
        boolean intoEnderchest = false;

        switch (event.getAction()) {
            case MOVE_TO_OTHER_INVENTORY: // shift click
                item = event.getCurrentItem();
                intoEnderchest = event.getClickedInventory() != null
                        && event.getClickedInventory().getType() != InventoryType.ENDER_CHEST;
                break;

            case HOTBAR_SWAP:
                Player player = (Player) event.getWhoClicked();
                item = player.getInventory().getItem(event.getHotbarButton());

                intoEnderchest = event.getClickedInventory() != null
                        && event.getClickedInventory().getType() == InventoryType.ENDER_CHEST;
                break;

            default:
                item = event.getCursor();
                intoEnderchest = event.getClickedInventory() != null
                        && event.getClickedInventory().getType() == InventoryType.ENDER_CHEST;
                break;
        }

        if (isShulkerBox(item) && intoEnderchest) {
            event.setCancelled(true);
            //SMPTweaks.getInstance().getLogger().info("Prevented shulkerbox from moving to enderchest! (inventory click)");
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getView().getTopInventory().getType() != InventoryType.ENDER_CHEST) return;

        int enderchestSize = event.getView().getTopInventory().getSize();

        for (int slot : event.getRawSlots()) {
            if (slot < enderchestSize) {
                ItemStack item = event.getNewItems().get(slot);
                if (isShulkerBox(item)) {
                    event.setResult(Event.Result.DENY);
                    //SMPTweaks.getInstance().getLogger().info("Prevented shulkerbox from moving to enderchest! (inventory drag)");
                    return;
                }
            }
        }
    }

    private boolean isShulkerBox(ItemStack item) {
        if (item == null) return false;
        Material type = item.getType();
        return type.name().endsWith("SHULKER_BOX");
    }

}
