package olmic.dungeoncrawler.commands;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.components.ComponentManager;
import olmic.dungeoncrawler.items.components.ItemComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class OpenComponentGUI implements CommandExecutor {

    private DungeonCrawler dungeonCrawler;

    public OpenComponentGUI(DungeonCrawler dungeonCrawler) {
        this.dungeonCrawler = dungeonCrawler;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        Inventory inv = Bukkit.createInventory(player, 54);
        ComponentManager componentManager = dungeonCrawler.getComponentManager();

        int i = 0;
        for (ItemComponent component : componentManager.components.values()) {

            inv.setItem(i, component.getItemStack());

            i++;
        }

        player.openInventory(inv);

        return true;
    }

}
