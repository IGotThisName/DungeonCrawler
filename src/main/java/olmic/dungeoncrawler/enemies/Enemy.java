package olmic.dungeoncrawler.enemies;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.stats.Stat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public abstract class Enemy {

    protected DungeonCrawler dungeonCrawler;

    protected double health;
    protected double damage;
    protected double speed;
    protected double sightRange;
    protected double attackRange;
    protected LivingEntity entity;
    protected boolean attacking;

    protected Player target;

    public Enemy(double health, double damage, double speed, double attackRange, DungeonCrawler dungeonCrawler) {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.attackRange = attackRange;
        this.dungeonCrawler = dungeonCrawler;
        this.attacking = false;

        new BukkitRunnable() {
            @Override
            public void run() {

                for (Entity near : entity.getNearbyEntities(attackRange, attackRange, attackRange)) {
                    if (near instanceof Player) {

                        target = (Player) near;
                        Vector dist = target.getEyeLocation().toVector().subtract(entity.getEyeLocation().toVector());
                        Vector viewDirection = entity.getEyeLocation().getDirection();

                        if (dist.length() <= attackRange) {
                            Vector toTarget = target.getEyeLocation().toVector().subtract(entity.getEyeLocation().toVector()).normalize();
                            double dotProduct = toTarget.dot(viewDirection);
                            Enemy enemy = dungeonCrawler.getEnemyManager().getEnemies().get(entity);

                            if (dotProduct >= 0.7) {
                                if (attacking == false) {
                                    Attack();
                                    attacking = true;
                                    entity.setAI(false);
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(dungeonCrawler.plugin, 0, 1);
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public double getHealth() {
        return health;
    }
    public double getDamage() {
        return damage;
    }
    public double getSpeed() {
        return speed;
    }
    public double getSightRange() {
        return sightRange;
    }
    public void Damage(double damage) {
        health -= damage;
        
        entity.damage(0);

        if (health < 0) {
            dungeonCrawler.getEnemyManager().getEnemies().remove(entity);
            entity.setHealth(0);
        }
    }
    public void Kill() {
        dungeonCrawler.getEnemyManager().getEnemies().remove(entity);
        entity.setHealth(0);
    }
    protected abstract void Attack();




}