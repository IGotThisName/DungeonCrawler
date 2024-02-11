package olmic.dungeoncrawler.stats;

public enum Stat {

    Cooldown("Cooldown"),
    MeleeDamage("Melee Damage"),
    RangedDamage("Ranged Damage"),
    MagicDamage("Magic Damage");

    public final String string;

    Stat(String string) {
        this.string = string;
    }
}
