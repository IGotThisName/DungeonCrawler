package olmic.dungeoncrawler.util;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.components.ComponentManager;
import olmic.dungeoncrawler.items.components.ItemComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class Utils {

    private static ComponentManager componentManager = DungeonCrawler.componentManager;



    public static void showComponentInv(Player player) {

        Inventory inv = Bukkit.createInventory(player, 54, "Components");

        int slot = 1;

        for (ItemComponent component : componentManager.components.values()) {

            inv.setItem(slot, component.getItemStack());

            slot ++;
        }

        player.openInventory(inv);
    }

}
