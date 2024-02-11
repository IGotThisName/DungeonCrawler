package olmic.dungeoncrawler.items.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.components.ComponentEffect;
import olmic.dungeoncrawler.items.components.ComponentManager;
import olmic.dungeoncrawler.items.components.Direction;
import olmic.dungeoncrawler.items.components.ItemComponent;
import olmic.dungeoncrawler.stats.Stat;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static olmic.dungeoncrawler.items.components.Direction.getOppositeDirection;

public class Item {

    private ComponentManager componentManager = DungeonCrawler.componentManager;

    private Material material;
    private String name;
    private HashMap<Stat, Double> stats;
    private ArrayList<String> components;
    private String uuid;

    public Item(Material material, String name, ArrayList<String> components, String uuid) {
        this.material = material;
        this.name = name;
        this.components = components;
        this.uuid = uuid;
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
    public HashMap<Stat, Double> getStats() {
        return stats;
    }

    public String getSlotKey(int index) {
        return components.get(index);
    }
    public void setSlotKey(int index, String key) {
        components.set(index, key);
    }
    public void updateStats() {

        for (int i = 0; i < components.size(); i++) {
            String componentKey = components.get(i);
            if (componentKey.equalsIgnoreCase("empty")) {
                // do nothing
            } else {

                ArrayList<ComponentEffect> appliedEffects = new ArrayList<>();

                for (Direction direction : Direction.values()) {

                    String otherKey = getKeyFromDirection(direction, i);

                    if (otherKey.equalsIgnoreCase("empty")) {
                        // do nothing
                    } else if (otherKey.equalsIgnoreCase("core")) {
                        // do nothing
                    }
                    else {

                        ItemComponent otherComponent = componentManager.components.get(otherKey);

                        Direction oppDir = getOppositeDirection(direction);

                        List<ComponentEffect> effects = otherComponent.getEffects();

                        for (ComponentEffect effect : effects) {
                            if (effect.getDirections().contains(oppDir)) {
                                appliedEffects.add(effect);

                                Bukkit.getLogger().info("applied effect to " + componentKey + " from " + otherKey);
                            }
                        }

                    }
                }

                // add to stats list


            }
        }
    }

    private String getKeyFromDirection(Direction direction, int slot) {

        int x = slot % 5;
        int y = (int) Math.floor(slot/5);

        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }

        if (x < 0 || x > 4 || y < 0 || y > 4) {
            return "empty";
        }

        int getSlot = y * 5 + x;
        return components.get(getSlot);
    }

    public static Item createEmpty(Material material, String name, String uuid) {

        ArrayList<String> components = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            if (i == 12) {
                components.add("core");
            } else {
                components.add("empty");
            }
        }

        return new Item(material, name, components, uuid);
    }

    private enum ComponentChar {

        COMPONENT("§e■"),
        CORE("§6■"),
        EMPTY("§7□");

        private String character;

        ComponentChar(String character) {
            this.character = character;
        }
    }

    private List<Component> createDescription() {
        List<Component> description = new ArrayList<>();

        // cycle through 5 times making a new component for each
        // in each component cycle through 5 times adding the character

        for (int line = 1; line <= 5; line++) {

            TextComponent component = Component.text("");

            for (int i = 0; i < 5; i++) {

                int num = i + (line - 1) * 5;

                switch(components.get(num)) {

                    case "empty":
                        component = component.append(Component.text("§7□ ").decoration(TextDecoration.ITALIC, false));
                        break;
                    case "core":
                        component = component.append(Component.text("§6■ ").decoration(TextDecoration.ITALIC, false));
                        break;
                    default:
                        component = component.append(Component.text("§e■ ").decoration(TextDecoration.ITALIC, false));
                        break;
                }
            }
            description.add(component);
        }

        return description;
    }

    public ItemStack getItemStack() {
        ItemStack newItem = new ItemStack(material);
        ItemMeta meta = newItem.getItemMeta();

        // modify meta
        meta.lore(createDescription());
        meta.displayName(Component.text(name).decoration(TextDecoration.ITALIC, false));

        // set item meta
        newItem.setItemMeta(meta);
        // set tags
        newItem = NBTutil.setNBT(newItem, "customItem", uuid);

        for (int i = 0; i < 25; i++) {
            newItem = NBTutil.setNBT(newItem, String.valueOf(i), components.get(i));
        }

        return newItem;
    }
}