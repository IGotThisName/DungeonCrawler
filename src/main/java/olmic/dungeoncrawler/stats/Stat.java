package olmic.dungeoncrawler.stats;

import net.kyori.adventure.text.format.TextColor;

public enum Stat {

    Cooldown("Cooldown", TextColor.color(0, 222, 255)),
    MeleeDamage("Melee Damage", TextColor.color(255, 0, 0)),
    RangedDamage("Ranged Damage", TextColor.color(0, 134, 0)),
    MagicDamage("Magic Damage", TextColor.color(168, 0, 145)),
    AttackSpeed("Attack Speed", TextColor.color(255, 255, 0)),
    NecroticDamage("Necrotic Damage", TextColor.color(85, 4, 77));

    public final String string;
    public final TextColor color;

    Stat(String string, TextColor color) {
        this.string = string;
        this.color = color;
    }
}
