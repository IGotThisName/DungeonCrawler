package olmic.dungeoncrawler.stats;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.attacks.shortsword.SwordAttacks;
import olmic.dungeoncrawler.attacks.wand.WandAttacks;
import olmic.dungeoncrawler.items.items.Item;
import olmic.dungeoncrawler.items.items.ItemManager;
import olmic.dungeoncrawler.items.items.WeaponType;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PlayerProfile {

    private HashMap<Stat, Double> stats;

    private Player player;
    private DungeonCrawler dungeonCrawler;

    public PlayerProfile(Player player, DungeonCrawler dungeonCrawler) {
        stats = new HashMap<Stat, Double>();
        this.dungeonCrawler = dungeonCrawler;
        this.player = player;

        for (Stat stat : Stat.values()) {
            stats.put(stat, 0d);
        }

        Bukkit.getPluginManager().registerEvents(new SwordAttacks(WeaponType.SHORTSWORD, dungeonCrawler, this),
                dungeonCrawler.plugin);
        Bukkit.getPluginManager().registerEvents(new WandAttacks(WeaponType.WAND, dungeonCrawler, this),
                dungeonCrawler.plugin);
    }



    private void resetStats() {
        for (Stat stat : Stat.values()) {
            stats.put(stat, 0d);
        }
    }

    public void addStat(Stat stat, Double value) {
        if (!stats.containsKey(stat)) {
            stats.put(stat, value);
        } else {
            double old = stats.get(stat);
            stats.put(stat, old + value);
        }
    }

    public void setStat(Stat stat, Double value) {
        stats.put(stat, value);
    }

    public double getStat(Stat stat) {
        return stats.get(stat);
    }

    public Player getPlayer() {
        return player;
    }

    public void updateStats() {

        ItemStack mainHand;

        resetStats();

        try {
            mainHand = player.getInventory().getItemInMainHand();
        } catch (Exception e) {
            mainHand = null;
        }

        String mainHandKey = NBTutil.getNBT(mainHand, "customItem");

        if (mainHand != null && mainHandKey != "") {
            // mainhand stats

            ItemManager itemManager = dungeonCrawler.getItemManager();

            Item item = itemManager.items.get(mainHandKey);
            HashMap<Stat, Double> itemStats = item.getStats();

            for (Stat stat : itemStats.keySet()) {
                addStat(stat, itemStats.get(stat));
            }
        }
    }
}