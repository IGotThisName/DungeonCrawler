package olmic.dungeoncrawler.items.items;

import olmic.dungeoncrawler.stats.PlayerStat;
import org.bukkit.Material;

import java.util.HashMap;

public class Item {

    private Material material;
    private String name;
    private HashMap<PlayerStat, Double> stats;

    Item(Material material, String name, HashMap<PlayerStat, Double> stats) {
        this.material = material;
        this.name = name;
        this.stats = stats;
    }

    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public HashMap<PlayerStat, Double> getStats() {
        return stats;
    }
    public void setStats(HashMap<PlayerStat, Double> stats) {
        this.stats = stats;
    }

}