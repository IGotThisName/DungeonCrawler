package olmic.dungeoncrawler.attacks.shortsword;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.attacks.Attacks;
import olmic.dungeoncrawler.enemies.Enemy;
import olmic.dungeoncrawler.items.items.WeaponType;
import olmic.dungeoncrawler.stats.PlayerProfile;
import olmic.dungeoncrawler.stats.Stat;
import olmic.dungeoncrawler.util.ParticleUtil;
import org.bukkit.*;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class SwordAttacks extends Attacks {

    public SwordAttacks(WeaponType type, DungeonCrawler dungeonCrawler, PlayerProfile profile) {
        super(type, dungeonCrawler, profile);
    }

    @Override
    protected void LeftAttack() {

        // set positions

        Location loc = player.getEyeLocation();
        Location up = loc.clone();
        up.setPitch(loc.getPitch() + 90F);

        double offset1 = Math.random();
        double offset2 = Math.random();

        if (Math.random()*2 >= 1) {
            offset1 *= -1;
        } else {
            offset2 *= -1;
        }

        Location pos1 = player.getEyeLocation()
                .add(player.getEyeLocation().getDirection().multiply(2).rotateAroundAxis(up.getDirection(), 30))
                .add(up.getDirection().multiply(offset1))
                .add(player.getEyeLocation().getDirection().multiply(1.5));
        Location pos2 = player.getEyeLocation()
                .add(player.getEyeLocation().getDirection().multiply(2).rotateAroundAxis(up.getDirection(), -30))
                .add(up.getDirection().multiply(offset2))
                .add(player.getEyeLocation().getDirection().multiply(1.5));


        ParticleUtil.Line(pos1, pos2, Color.fromRGB(189, 242, 255));

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 1f);

        /* FUN DAMAGE STUFF */

        Vector playerLocation = player.getEyeLocation().toVector();
        Vector viewDirection = player.getEyeLocation().getDirection().normalize();

        // for each enemy

        for (Entity entity : player.getNearbyEntities(4, 4, 4)) {
            if (dungeonCrawler.getEnemyManager().getEnemies().containsKey(entity)) {

                LivingEntity entity1 = (LivingEntity) entity;
                Vector dist = entity1.getEyeLocation().toVector().subtract(playerLocation);

                if (dist.length() <= 3) {
                    Vector toTarget = entity1.getEyeLocation().toVector().subtract(playerLocation).normalize();
                    double dotProduct = toTarget.dot(viewDirection);
                    Enemy enemy = dungeonCrawler.getEnemyManager().getEnemies().get(entity);

                    if (dotProduct >= 0.63) {
                        entity1.damage(0);
                        enemy.Damage(profile.getStat(Stat.MeleeDamage));
                    }
                }
            }
        }

        // cooldown
        SetLCooldown(20);
    }

    @Override
    protected void RightAttack() {

        if (!player.isSneaking()) {
            player.setVelocity(player.getEyeLocation().getDirection().multiply(new Vector(1, 0, 1)).add(new Vector(0, 0.4, 0)));
        } else {
            player.setVelocity(player.getEyeLocation().getDirection().multiply(new Vector(1, 0, 1).multiply(-1)).add(new Vector(0, 0.4, 0)));
        }

        ParticleUtil.Circle(player.getLocation(), 1, 1f, Color.WHITE);

        new BukkitRunnable() {

            private int num = 0;

            @Override
            public void run() {
                if (num < 12) {
                    player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation(), 5,
                            new Particle.DustOptions(Color.WHITE, 2));
                } else {
                    cancel();
                }
                num++;
            }

        }.runTaskTimer(dungeonCrawler.plugin, 0, 1);

        SetRCooldown(100);
    }
}