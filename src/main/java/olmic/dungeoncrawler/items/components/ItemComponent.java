package olmic.dungeoncrawler.items.components;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import olmic.dungeoncrawler.util.Keys;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ItemComponent {

    // all are required
    private Material material;
    private String name;
    private List<ComponentEffect> effects;
    private String key;



    ItemComponent(Material material, String name, List<ComponentEffect> effects, String key) {
        this.material = material;
        this.name = name;
        this.effects = effects;
        this.key = key;
    }

    public ItemStack getItemStack() {

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.displayName(Component.text(name));
        itemMeta.lore(buildDescription());
        itemMeta.getPersistentDataContainer().set(Keys.componentKey, PersistentDataType.STRING, key);

        item.setItemMeta(itemMeta);
        return item;
    }

    public List<Component> buildDescription() {

        List<Component> lore = new ArrayList<>();

        // for each effect
        for (int i = 0; i < effects.size(); i++) {

            // set up
            ComponentEffect effect = effects.get(i);
            String effectDesc1 = "";
            String effectDesc2 = "";
            String directionString = "";

            for (int n = 0; n < effect.getDirections().size() - 1; n++) {
                directionString += effect.getDirections().get(n).descName + " and ";
            }

            directionString += effect.getDirections().get(effect.getDirections().size()-1).descName;

            effectDesc1 += directionString + " gain:";
            effectDesc2 += effect.getValue() + "% " + effect.getStat().string;

            // add to list
            lore.add(Component.text(effectDesc1).decoration(TextDecoration.ITALIC, false));
            lore.add(Component.text(effectDesc2).decoration(TextDecoration.ITALIC, false));
        }

        lore.add(Component.text(""));

        HashSet<Direction> directions = new HashSet<>();
        for (int i = 0; i < effects.size(); i++) {
            for (int n = 0; n < effects.get(i).getDirections().size(); n++) {
                directions.add(effects.get(i).getDirections().get(n));
            }
        }

        // above slot
        if (directions.contains(Direction.UP)) {
            lore.add(Component.text("     §e■").decoration(TextDecoration.ITALIC, false));
        } else  {
            lore.add(Component.text("     §e□").decoration(TextDecoration.ITALIC, false));
        }

        // left and right slot
        if (directions.contains(Direction.LEFT) && directions.contains(Direction.RIGHT)) {
            lore.add(Component.text("  ").decoration(TextDecoration.BOLD, true).append(Component.text("§e■ §6■ §e■").decoration(TextDecoration.ITALIC, false).decoration(TextDecoration.BOLD, false)));
        } else if (directions.contains(Direction.LEFT) && !directions.contains(Direction.RIGHT)) {
            lore.add(Component.text("  ").decoration(TextDecoration.BOLD, true).append(Component.text("§e■ §6■ §e□").decoration(TextDecoration.ITALIC, false).decoration(TextDecoration.BOLD, false)));
        } else if (!directions.contains(Direction.LEFT) && directions.contains(Direction.RIGHT)) {
            lore.add(Component.text("  ").decoration(TextDecoration.BOLD, true).append(Component.text("§e□ §6■ §e■").decoration(TextDecoration.ITALIC, false).decoration(TextDecoration.BOLD, false)));
        } else {
            lore.add(Component.text("  ").decoration(TextDecoration.BOLD, true).append(Component.text("§e□ §6■ §e□").decoration(TextDecoration.ITALIC, false).decoration(TextDecoration.BOLD, false)));
        }

        // below slot
        if (directions.contains(Direction.DOWN)) {
            lore.add(Component.text("     §e■").decoration(TextDecoration.ITALIC, false));
        } else  {
            lore.add(Component.text("     §e□").decoration(TextDecoration.ITALIC, false));
        }

        return lore;
    }
}