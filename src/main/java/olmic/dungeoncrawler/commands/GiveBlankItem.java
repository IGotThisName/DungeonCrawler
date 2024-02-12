package olmic.dungeoncrawler.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.items.Item;
import olmic.dungeoncrawler.items.items.ItemManager;
import olmic.dungeoncrawler.items.items.WeaponType;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class GiveBlankItem implements CommandExecutor, TabCompleter {

    public GiveBlankItem(DungeonCrawler dungeonCrawler) {
        this.dungeonCrawler = dungeonCrawler;
    }

    private DungeonCrawler dungeonCrawler;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) { return false; }

        Player player = (Player) sender;

        if (!label.equalsIgnoreCase("giveBlankItem")) { return false; }

        WeaponType type;
        String uuid = UUID.randomUUID().toString();

        try {
            type = WeaponType.valueOf(args[0]);
        } catch(IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Illegal weapon type");
            return false;
        }

        Item blankItem = Item.createEmpty(type, uuid, dungeonCrawler);
        dungeonCrawler.getItemManager().items.put(uuid, blankItem);
        ItemStack newItem = blankItem.getItemStack();

        player.getInventory().addItem(newItem);

        return true;
    }

    // tab completion
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        final List<String> completions = new ArrayList<>();

        List<String> commands = new ArrayList<>();

        if (args.length == 1) {
            for (WeaponType type : WeaponType.values()) {
                commands.add(type.toString());
            }
        }

        StringUtil.copyPartialMatches(args[0], commands, completions);
        //sort the list
        Collections.sort(completions);
        return completions;
    }
}
