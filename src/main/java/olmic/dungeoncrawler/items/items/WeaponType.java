package olmic.dungeoncrawler.items.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;

public enum WeaponType {

    SHORTSWORD(Material.IRON_SWORD,
            Component.text("Shortsword").decoration(TextDecoration.ITALIC, false).color(TextColor.color(255, 255, 0)));

    private Material material;
    private Component itemName;

    WeaponType(Material material, Component itemName) {
        this.material = material;
        this.itemName = itemName;
    }

}
