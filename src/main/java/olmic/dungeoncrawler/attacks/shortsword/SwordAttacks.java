package olmic.dungeoncrawler.attacks.shortsword;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.attacks.Attacks;
import olmic.dungeoncrawler.items.items.WeaponType;
import olmic.dungeoncrawler.stats.PlayerProfile;
import olmic.dungeoncrawler.util.ParticleUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class SwordAttacks extends Attacks {

    public SwordAttacks(WeaponType type, DungeonCrawler dungeonCrawler) {
        super(type, dungeonCrawler);
    }

    @Override
    protected void LeftAttack() {
        Location playerLoc = player.getLocation();

        Vector yUp = new Vector(0, 0.5, 0);
        Vector yDown = new Vector(0, -0.5, 0);

        Location pos1 = playerLoc.add(player.getLocation().getDirection().rotateAroundY(30).add(yUp));
        Location pos2 = playerLoc.add(player.getLocation().getDirection().rotateAroundY(-30).add(yDown));

        ParticleUtil.Slash(pos1, pos2);
    }

    @Override
    protected void RightAttack() {

    }
}