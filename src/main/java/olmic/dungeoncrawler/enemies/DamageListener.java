package olmic.dungeoncrawler.enemies;

import olmic.dungeoncrawler.DungeonCrawler;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class DamageListener implements Listener {

    /* @EventHandler
    public void DamageEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof LivingEntity) {
            event.setCancelled(true);
        }
    } */

    private DungeonCrawler dungeonCrawler;

    public DamageListener(DungeonCrawler dungeonCrawler) {
        this.dungeonCrawler = dungeonCrawler;
    }

    @EventHandler
    public void EntityDamageEntity(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

    /* @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {
        EnemyManager enemyManager = dungeonCrawler.getEnemyManager();

        if (event.getEntity() instanceof LivingEntity) {
            if (enemyManager.getEnemies().containsKey(event.getEntity())) {

                Enemy enemy = enemyManager.getEnemies().get(event.getEntity());

                enemy.Damage(event.getDamage());

                ((LivingEntity) event.getEntity()).setHealth(((LivingEntity) event.getEntity()).getMaxHealth());

                Bukkit.getLogger().info("damaged enemy for: " + event.getDamage());
            }
        }
    } */

    @EventHandler
    public void EntityDeathEvent(EntityDeathEvent event) {
        if (dungeonCrawler.getEnemyManager().getEnemies().containsKey(event.getEntity())) {
            dungeonCrawler.getEnemyManager().getEnemies().remove(event.getEntity());
        }
    }

}
