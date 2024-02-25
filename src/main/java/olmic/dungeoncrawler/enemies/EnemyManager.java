package olmic.dungeoncrawler.enemies;

import olmic.dungeoncrawler.DungeonCrawler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.HashSet;

public class EnemyManager {

    private HashMap<LivingEntity, Enemy> enemies;
    private DungeonCrawler dungeonCrawler;

    public EnemyManager(DungeonCrawler dungeonCrawler) {
        this.dungeonCrawler = dungeonCrawler;
        enemies = new HashMap<>();
    }

    public void CreateEnemy(Enemy enemy) {
        enemy.getEntity();
        enemies.put(enemy.getEntity(), enemy);

        for (Enemy enemy1 : enemies.values()) {
            Bukkit.getLogger().info(enemy1.toString());
        }
    }

    public HashMap<LivingEntity, Enemy> getEnemies() {
        return enemies;
    }

    public void KillAll() {
        for (LivingEntity entity : enemies.keySet()) {
            entity.setHealth(0);
        }
    }
}