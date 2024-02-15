package olmic.dungeoncrawler.attacks.wand;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.attacks.Attacks;
import olmic.dungeoncrawler.items.items.WeaponType;
import olmic.dungeoncrawler.stats.PlayerProfile;
import org.bukkit.Bukkit;

public class WandAttacks extends Attacks {

    public WandAttacks(WeaponType type, DungeonCrawler dungeonCrawler, PlayerProfile profile) {
        super(type, dungeonCrawler, profile);
    }

    @Override
    protected void LeftAttack() {

    }

    @Override
    protected void RightAttack() {

    }
}
