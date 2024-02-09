package olmic.dungeoncrawler.commands;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.items.Item;
import olmic.dungeoncrawler.items.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GiveBlankItem implements CommandExecutor {

    private final ItemManager itemManager = DungeonCrawler.itemManager;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) { return false; }

        Player player = (Player) sender;

        if (!label.equalsIgnoreCase("giveBlankItem")) { return false; }

        Material type = null;
        String name = "";
        String uuid = UUID.randomUUID().toString();

        if (args.length <= 1) {
            sender.sendMessage("no item type");
            return false;
        }

        try {
            type = Material.getMaterial(args[0]);
        } catch(IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Illegal item type");
            return false;
        }

        // return if too short
        if (args.length <= 2) {
            sender.sendMessage("no item name");
            return false;
        }

        for (int i = 1; i < args.length - 1; i++) {
            name += args[i] + " ";
        }
        name += args[args.length-1];

        Item blankItem = Item.createEmpty(type, name);

        itemManager.items.put(uuid, blankItem);

        ItemStack newItem = new ItemStack(type);
        // net.minecraft.server.v1_8_R1.ItemStack stack = CraftItemStack.asNMSCopy(newItem);


        return true;
    }
}
