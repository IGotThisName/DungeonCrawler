package olmic.dungeoncrawler.items.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import olmic.dungeoncrawler.stats.Stat;
import org.bukkit.Material;

import java.util.HashMap;

public enum WeaponType {

    SHORTSWORD(Material.IRON_SWORD,
            Component.text("Shortsword").decoration(TextDecoration.ITALIC, false).color(TextColor.color(195, 161, 0)),
            new HashMap<Stat, Double>() {{
                put(Stat.MeleeDamage, 15d);
                put(Stat.AttackSpeed, 10d);
            }}),
    WAND(Material.STICK,
            Component.text("Wand").decoration(TextDecoration.ITALIC, false).color(TextColor.color(155, 0, 155)),
            new HashMap<Stat, Double>() {{
                put(Stat.MagicDamage, 10d);
                put(Stat.AttackSpeed, 15d);
            }});

    public Material material;
    public Component itemName;
    public HashMap<Stat, Double> baseStats;

    WeaponType(Material material, Component itemName, HashMap<Stat, Double> baseStats) {
        this.material = material;
        this.itemName = itemName;
        this.baseStats = baseStats;
    }
}
