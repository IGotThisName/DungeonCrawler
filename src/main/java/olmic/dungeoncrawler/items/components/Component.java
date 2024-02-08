package olmic.dungeoncrawler.items.components;

import olmic.dungeoncrawler.stats.ComponentStat;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Component {

    // all are required
    private Material material;
    private String name;
    private List<ComponentEffect> effects;

    Component(Material material, String name, List<ComponentEffect> effects) {
        this.material = material;
        this.name = name;
        this.effects = effects;
    }

    public List<String> buildDescription() {

        ArrayList<String> lore = new ArrayList<>();

        // for each effect
        for (int i = 0; i < effects.size(); i++) {

            // set up
            ComponentEffect effect = effects.get(i);
            String effectDesc = "";
            String directionString = "";

            for (int n = 0; n < effect.getDirections().size() - 1; n++) {
                directionString += effect.getDirections().get(n).descName + " and ";
            }

            directionString += effect.getDirections().get(effect.getDirections().size()-1).descName;

            effectDesc += "Components " + directionString + " gain " + effect.getValue() + "% " + effect.getStat().getString();

            // add to list
            lore.add(effectDesc);
        }

        lore.add("");

        HashSet<Direction> directions = new HashSet<>();
        for (int i = 0; i < effects.size(); i++) {
            for (int n = 0; n < effects.get(i).getDirections().size(); n++) {
                directions.add(effects.get(i).getDirections().get(n));
            }
        }

        // above slot
        if (directions.contains(Direction.UP)) {
            lore.add("  §e■");
        } else  {
            lore.add("  §e▢");
        }

        // left and right slot
        if (directions.contains(Direction.LEFT) && directions.contains(Direction.RIGHT)) {
            lore.add("§e■§6■§e■");
        } else if (directions.contains(Direction.LEFT) && !directions.contains(Direction.RIGHT)) {
            lore.add("§e■§6■§e▢");
        } else if (!directions.contains(Direction.LEFT) && directions.contains(Direction.RIGHT)) {
            lore.add("§e▢§6■§e■");
        } else {
            lore.add("§e▢§6■§e▢");
        }

        // below slot
        if (directions.contains(Direction.DOWN)) {
            lore.add("  §e■");
        } else  {
            lore.add("  §e▢");
        }

        return lore;
    }
}
