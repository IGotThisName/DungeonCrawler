package olmic.dungeoncrawler.items.components;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;

public class Component {

    // all are required
    private Material material;
    private String name;
    private HashMap<ComponentEffect, Direction[]> effects;

    Component(Material material, String name, HashMap<ComponentEffect, Direction[]> effects) {
        this.material = material;
        this.name = name;
        this.effects = effects;
    }
}
