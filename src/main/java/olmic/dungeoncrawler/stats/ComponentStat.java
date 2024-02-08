package olmic.dungeoncrawler.stats;

public enum ComponentStat {

    CooldownMulti("Cooldown Multiplier"),
    MeleeDamageMulti("Melee Damage Multiplier"),
    RangedDamageMulti("Ranged Damage Multiplier"),
    MagicDamageMulti("Magic Damage Multiplier");

    public final String string;

    ComponentStat(String string) {
        this.string = string;
    }
}
