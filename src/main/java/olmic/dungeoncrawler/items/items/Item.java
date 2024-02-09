package olmic.dungeoncrawler.items.items;

import olmic.dungeoncrawler.items.components.ItemComponent;
import olmic.dungeoncrawler.items.skills.Skill;
import olmic.dungeoncrawler.stats.PlayerStat;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;

public class Item {

    private Material material;
    private String name;
    private HashMap<PlayerStat, Double> stats;
    private ArrayList<String> components;

    Item(Material material, String name, ArrayList<String> components) {
        this.material = material;
        this.name = name;
        this.components = components;
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

    public static Item createEmpty(Material material, String name) {

        ArrayList<String> components = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            if (i == 12) {
                components.add("core");
            } else {
                components.add("empty");
            }
        }

        return new Item(material, name, components);
    }
}