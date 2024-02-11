package olmic.dungeoncrawler.items;

import net.kyori.adventure.text.TextComponent;
import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.items.Item;
import olmic.dungeoncrawler.items.items.ItemManager;
import olmic.dungeoncrawler.util.GUITEM;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.EventListener;
import java.util.HashMap;

public class ItemEditor implements Listener {

    private ItemManager itemManager = DungeonCrawler.itemManager;

    private static final HashMap<Integer, Integer> slotMap = new HashMap<>();

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        slotMap.put(2, 0);
        slotMap.put(3, 1);
        slotMap.put(4, 2);
        slotMap.put(5, 3);
        slotMap.put(6, 4);
        slotMap.put(11, 5);
        slotMap.put(12, 6);
        slotMap.put(13, 7);
        slotMap.put(14, 8);
        slotMap.put(15, 9);
        slotMap.put(20, 10);
        slotMap.put(21, 11);
        slotMap.put(22, 12);
        slotMap.put(23, 13);
        slotMap.put(24, 14);
        slotMap.put(29, 15);
        slotMap.put(30, 16);
        slotMap.put(31, 17);
        slotMap.put(32, 18);
        slotMap.put(33, 19);
        slotMap.put(38, 20);
        slotMap.put(39, 21);
        slotMap.put(40, 22);
        slotMap.put(41, 23);
        slotMap.put(42, 24);

        TextComponent textName = (TextComponent) event.getView().title();
        String name = textName.content();
        Player player = (Player) event.getWhoClicked();
        if (name.equalsIgnoreCase("Item Editor")) {

            // 3 cases, taking component out, putting component in, getting component from inv
            Inventory inv = event.getInventory();

            ItemStack slotItem = event.getCurrentItem();
            ItemStack cursorItem = event.getCursor();

            Item item = itemManager.items.get(NBTutil.getNBT(inv.getItem(49), "customItem"));

            if (event.getRawSlot() >= inv.getSize()) {
                // bottom inv


                if (NBTutil.getNBT(slotItem, "component") != "") {
                    // item in slot is component, allow event
                    event.setCancelled(false);
                }
                else if (NBTutil.getNBT(cursorItem, "component") != "" && slotItem.getType() == Material.AIR) {
                    // putting held item into empty slot, allow event
                    event.setCancelled(false);
                }
                else if (NBTutil.getNBT(cursorItem, "component") != "" && NBTutil.getNBT(slotItem, "component") != "") {
                    // swapping between components, allow event
                    event.setCancelled(false);
                }
                else {
                    event.setCancelled(true);
                }
            } else {
                // top inv

                if (NBTutil.getNBT(slotItem, "emptySlot") == "true" && NBTutil.getNBT(cursorItem, "component") != "") {

                    // put component into slot
                    inv.setItem(event.getSlot(), new ItemStack(Material.AIR));

                    item.setSlotKey(slotMap.get(event.getSlot()), NBTutil.getNBT(cursorItem, "component"));

                    event.setCancelled(false);
                } else if (NBTutil.getNBT(slotItem, "component") != "" && cursorItem.getType() == Material.AIR) {
                    // take out component

                    player.setItemOnCursor(GUITEM.EMPTY_SLOT.item);

                    item.setSlotKey(slotMap.get(event.getSlot()), "empty");

                    event.setCancelled(false);
                } else if (NBTutil.getNBT(slotItem, "component") != "" && NBTutil.getNBT(cursorItem, "component") != "") {
                    // swap components

                    item.setSlotKey(slotMap.get(event.getSlot()), NBTutil.getNBT(cursorItem, "component"));

                    event.setCancelled(false);
                } else {
                    event.setCancelled(true);
                }

                item.updateStats();

                player.getInventory().setItemInMainHand(item.getItemStack());
                inv.setItem(49, item.getItemStack());

                itemManager.items.put(NBTutil.getNBT(inv.getItem(49), "customItem"), item);
            }
        }
    }
}
