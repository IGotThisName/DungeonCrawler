package olmic.dungeoncrawler.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum GUITEM {

    BACKGROUND(createItem(Material.BLACK_STAINED_GLASS_PANE, Component.text(""))),
    EMPTY_SLOT(NBTutil.setNBT(createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, Component.text("")), "emptySlot",
            "true")),
    CORE(NBTutil.setNBT(createItem(Material.ORANGE_STAINED_GLASS_PANE,
                    Component.text("Core").decoration(TextDecoration.ITALIC, false).decoration(TextDecoration.BOLD,
                            true)), "core",
            "true"));

    public ItemStack item;

    GUITEM(ItemStack item) {
        this.item = item;
    }

    private static ItemStack createItem(Material type, Component name) {
        ItemStack item = new ItemStack(type);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.displayName(name);

        item.setItemMeta(itemMeta);
        return item;
    }

}
