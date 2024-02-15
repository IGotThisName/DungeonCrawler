package olmic.dungeoncrawler.items.components;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

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

    public List<ComponentEffect> getEffects() {
        return effects;
    }

    public ItemStack getItemStack() {

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.displayName(Component.text(name).decoration(TextDecoration.ITALIC, false));
        itemMeta.lore(buildDescription());

        item.setItemMeta(itemMeta);
        item = NBTutil.setNBT(item, "component", key);
        return item;
    }

    public List<Component> buildDescription() {

        List<TextColor> goodColors = Arrays.asList(new TextColor[]{
                TextColor.color(255, 255, 0),
                TextColor.color(0, 255, 0),
                TextColor.color(0, 255, 255),
                TextColor.color(0, 0, 255)
        });

        List<TextColor> badColors = Arrays.asList(new TextColor[]{
                TextColor.color(82, 0, 33),
                TextColor.color(105, 0, 0),
                TextColor.color(96, 16, 0),
                TextColor.color(111, 66, 0)
        });

        // defaults
        HashMap<Direction, TextComponent> directions = new HashMap<>();
        directions.put(Direction.UP, Component.text("§7□").decoration(TextDecoration.BOLD, false));
        directions.put(Direction.DOWN, Component.text("§7□").decoration(TextDecoration.BOLD, false));
        directions.put(Direction.LEFT, Component.text("§7□").decoration(TextDecoration.BOLD, false));
        directions.put(Direction.RIGHT, Component.text("§7□").decoration(TextDecoration.BOLD, false));

        List<Component> lore = new ArrayList<>();

        // for each effect
        for (int i = 0; i < effects.size(); i++) {
            ComponentEffect effect = effects.get(i);
            String effectDesc2 = "";
            effectDesc2 += effect.getTextValue() + " " + effect.getStat().string;

            // add to list
            TextColor chosenColor = null;

            if (effect.getValue() >= 0) {
                // good color
                chosenColor = goodColors.get(i);
            } else {
                // bad color
                chosenColor = badColors.get(i);
            }

            lore.add(Component.text(effectDesc2).decoration(TextDecoration.ITALIC, false).color(chosenColor));

            // set direction chars
            for (int n = 0; n < effect.getDirections().size(); n++) {
                directions.put(effect.getDirections().get(n),
                        Component.text("■").color(chosenColor).decoration(TextDecoration.BOLD, false));
            }
        }

        lore.add(Component.text(""));

        lore.add(Component.text("     ")
                .append(directions.get(Direction.UP)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("  ").decoration(TextDecoration.BOLD, true)
                .append(directions.get(Direction.LEFT).decoration(TextDecoration.BOLD, false))
                .append(Component.text(" §6■ ").decoration(TextDecoration.BOLD, false))
                .append(directions.get(Direction.RIGHT).decoration(TextDecoration.BOLD, false))
                .decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("     ")
                .append(directions.get(Direction.DOWN)).decoration(TextDecoration.ITALIC, false));

        return lore;
    }
}