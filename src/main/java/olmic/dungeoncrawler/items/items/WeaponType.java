package olmic.dungeoncrawler.items.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;

public enum WeaponType {

    SHORTSWORD(Material.IRON_SWORD,
            Component.text("Shortsword").decoration(TextDecoration.ITALIC, false).color(TextColor.color(195, 161, 0))),
    WAND(Material.STICK,
            Component.text("Wand").decoration(TextDecoration.ITALIC, false).color(TextColor.color(155, 0, 155)));

    public Material material;
    public Component itemName;

    WeaponType(Material material, Component itemName) {
        this.material = material;
        this.itemName = itemName;
    }
}
