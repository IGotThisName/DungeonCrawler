package olmic.dungeoncrawler.attacks.shortsword;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.attacks.Attacks;
import olmic.dungeoncrawler.items.items.WeaponType;
import olmic.dungeoncrawler.stats.PlayerProfile;
import olmic.dungeoncrawler.util.ParticleUtil;
import org.bukkit.*;
import org.bukkit.util.Vector;

import java.util.Random;

public class SwordAttacks extends Attacks {

    public SwordAttacks(WeaponType type, DungeonCrawler dungeonCrawler, PlayerProfile profile) {
        super(type, dungeonCrawler, profile);
    }

    @Override
    protected void LeftAttack() {
        if (cooldown > 0) return;

        // set positions

        Location pos1 = player.getEyeLocation()
                .add(player.getEyeLocation().getDirection().multiply(2).rotateAroundAxis(new Vector(0, 1, 0), 30))
                .add(new Vector(0, Math.random(), 0))
                .add(player.getEyeLocation().getDirection().multiply(1.5));
        Location pos2 = player.getEyeLocation()
                .add(player.getEyeLocation().getDirection().multiply(2).rotateAroundAxis(new Vector(0, 1, 0), -30))
                .add(new Vector(0, -Math.random(), 0))
                .add(player.getEyeLocation().getDirection().multiply(1.5));


        // get distance between
        double dist = pos1.toVector().distance(pos2.toVector());

        // loop through for each particle
        for (double i = 0; i < dist; i += 0.1) {

            double progress = i / dist;

            pos2.getWorld().spawnParticle(Particle.REDSTONE,
                    Lerp(pos1.toVector(), pos2.toVector(), progress).toLocation(pos1.getWorld()), 1, new Particle.DustOptions(Color.fromRGB(189,
                            242, 255), 1));
        }

        // cooldown
        SetCooldown(10);
    }

    private Vector Lerp(Vector a, Vector b, double f) {
        double x = a.getX() * (1.0 - f) + (b.getX() * f);
        double y = a.getY() * (1.0 - f) + (b.getY() * f);
        double z = a.getZ() * (1.0 - f) + (b.getZ() * f);
        return new Vector(x, y, z);
    }

    @Override
    protected void RightAttack() {

    }
}