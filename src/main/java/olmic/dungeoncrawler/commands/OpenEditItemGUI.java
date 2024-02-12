package olmic.dungeoncrawler.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.components.ComponentManager;
import olmic.dungeoncrawler.items.components.ItemComponent;
import olmic.dungeoncrawler.items.items.Item;
import olmic.dungeoncrawler.items.items.ItemManager;
import olmic.dungeoncrawler.util.GUITEM;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class OpenEditItemGUI implements CommandExecutor {

    public OpenEditItemGUI(DungeonCrawler dungeonCrawler) {
        this.dungeonCrawler = dungeonCrawler;
    }

    private DungeonCrawler dungeonCrawler;
    private static final HashMap<Integer, Integer> slotMap = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

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

        if (!(sender instanceof Player)) { return false; }
        if (!label.equalsIgnoreCase("openEditItemGUI")) { return false; }

        Player player = (Player) sender;

        ItemManager itemManager = dungeonCrawler.getItemManager();
        ComponentManager componentManager = dungeonCrawler.getComponentManager();

        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage("please hold an item dumbass");
            return false;
        }

        ItemStack heldItem = player.getInventory().getItemInMainHand();
        Item item = itemManager.items.get(NBTutil.getNBT(heldItem, "customItem"));

        if (NBTutil.getNBT(heldItem, "customItem") == "") {
            player.sendMessage("please hold a custom item");
            return false;
        }

        Inventory inv = Bukkit.createInventory(player, 54, "Item Editor");

        // create the inv
        for (int x = 0; x < inv.getSize(); x++) {
            inv.setItem(x, GUITEM.BACKGROUND.item);
        }

        for (int line = 1; line <= 5; line++) {
            for (int i = 0; i < 5; i++) {
                int num = i + (line - 1) * 9 + 2;

                switch (item.getSlotKey(slotMap.get(num))) {
                    case "empty":
                    case "core":
                        inv.setItem(num, GUITEM.EMPTY_SLOT.item);
                        break;
                    default:
                        ItemComponent component = componentManager.components.get(item.getSlotKey(slotMap.get(num)));
                        inv.setItem(num, component.getItemStack());
                }
            }
        }

        inv.setItem(49, heldItem);
        inv.setItem(22, GUITEM.CORE.item);

        player.openInventory(inv);

        return false;
    }
}