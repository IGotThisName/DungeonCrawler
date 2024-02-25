package olmic.dungeoncrawler.enemies.enemyClasses;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.enemies.Enemy;
import olmic.dungeoncrawler.util.ParticleUtil;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class DormantGolem extends Enemy {
    public DormantGolem(Location location, DungeonCrawler dungeonCrawler) {
        super(40, 10, 1, 1.5, dungeonCrawler);
        entity = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
    }

    @Override
    protected void Attack() {

        ParticleUtil.Circle(entity.getLocation().add(entity.getLocation().getDirection()), 1, 2.5f, Color.WHITE);

        new BukkitRunnable() {

            private int num = 0;

            @Override
            public void run() {

                if (num > 20) {
                    cancel();
                    attacking = false;
                    entity.setAI(true);

                    Vector dist = target.getEyeLocation().toVector().subtract(entity.getEyeLocation().toVector());
                    Vector viewDirection = entity.getEyeLocation().getDirection();

                    if (dist.length() <= attackRange) {
                        Vector toTarget = target.getEyeLocation().toVector().subtract(entity.getEyeLocation().toVector()).normalize();
                        double dotProduct = toTarget.dot(viewDirection);
                        Enemy enemy = dungeonCrawler.getEnemyManager().getEnemies().get(entity);

                        if (dotProduct >= 0.3) {

                            dungeonCrawler.getProfileManager().getPlayerProfiles().get(target).Damage(damage);
                        }
                    }

                    // ParticleUtil.Slash(entity.getEyeLocation(), entity.getEyeLocation().getDirection(), attackRange,
                    //        30);

                    Location loc = entity.getEyeLocation();
                    Location up = loc.clone();
                    up.setPitch(loc.getPitch() + 90F);

                    double offset1 = Math.random();
                    double offset2 = Math.random();

                    if (Math.random()*2 >= 1) {
                        offset1 *= -1;
                    } else {
                        offset2 *= -1;
                    }

                    Location pos1 = entity.getEyeLocation()
                            .add(entity.getEyeLocation().getDirection().multiply(2).rotateAroundAxis(up.getDirection(), 30))
                            .add(up.getDirection().multiply(offset1))
                            .add(entity.getEyeLocation().getDirection().multiply(1.5));
                    Location pos2 = entity.getEyeLocation()
                            .add(entity.getEyeLocation().getDirection().multiply(2).rotateAroundAxis(up.getDirection(),
                                    -30))
                            .add(up.getDirection().multiply(offset2))
                            .add(entity.getEyeLocation().getDirection().multiply(1.5));

                    ParticleUtil.Line(pos1, pos2, Color.RED);

                    cancel();
                }

                num++;
            }
        }.runTaskTimer(dungeonCrawler.plugin, 0, 1);
    }
}
