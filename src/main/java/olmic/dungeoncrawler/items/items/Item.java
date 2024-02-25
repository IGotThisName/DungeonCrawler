package olmic.dungeoncrawler.items.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.components.*;
import olmic.dungeoncrawler.stats.Stat;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static olmic.dungeoncrawler.items.components.Direction.getOppositeDirection;

public class Item {

    private DungeonCrawler dungeonCrawler;

    private WeaponType type;
    private HashMap<Stat, Double> stats;
    private ArrayList<String> components;
    private String uuid;

    public Item(WeaponType type, ArrayList<String> components, String uuid, DungeonCrawler dungeonCrawler) {
        this.components = components;
        this.uuid = uuid;
        this.type = type;
        this.dungeonCrawler = dungeonCrawler;

        stats = new HashMap<>();

        updateStats();
    }

    public WeaponType getType() {
        return type;
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

    private void addStat(Stat stat, Double value) {
        if (!stats.containsKey(stat)) {
            stats.put(stat, value);
        } else {
            double old = stats.get(stat);
            stats.put(stat, old + value);
        }
    }

    public void updateStats() {

        stats.clear();

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

                        ComponentManager componentManager = dungeonCrawler.getComponentManager();
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

                // add to stats
                HashMap<Stat, Double> baseStats = new HashMap<>();
                HashMap<Stat, Double> statMulti = new HashMap<>();

                if (componentKey.equalsIgnoreCase("core")) {
                    for (Stat stat : type.baseStats.keySet()) {
                        baseStats.put(stat, type.baseStats.get(stat));
                    }
                }

                for (ComponentEffect effect : appliedEffects) {

                    Stat stat = effect.getStat();
                    Double value = effect.getValue();

                    if (effect.getOperation() == Operation.ADD){
                        if (!baseStats.containsKey(stat)) {
                            baseStats.put(stat, value);
                        } else {
                            double old = baseStats.get(stat);
                            baseStats.put(stat, old + value);
                        }
                    } else {
                        if (!statMulti.containsKey(stat)) {
                            statMulti.put(stat, value);
                        } else {
                            double old = statMulti.get(stat);
                            statMulti.put(stat, old + value);
                        }
                    }
                }

                for (Stat stat : Stat.values()) {

                    if (baseStats.containsKey(stat)) {

                        double baseValue = baseStats.get(stat);

                        if (statMulti.containsKey(stat)) {
                            baseValue = baseValue + baseValue * statMulti.get(stat);
                        }

                        addStat(stat, baseValue);
                    }
                }
            }
        }

        for (Stat stat : stats.keySet()) {
            Bukkit.getLogger().info(stat.string + " " + stats.get(stat));
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

    public static Item createEmpty(WeaponType type, String uuid, DungeonCrawler dungeonCrawler) {

        ArrayList<String> components = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            if (i == 12) {
                components.add("core");
            } else {
                components.add("empty");
            }
        }

        return new Item(type, components, uuid, dungeonCrawler);
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

        // stats

        for (Stat stat : stats.keySet()) {

            double value = stats.get(stat);

            if (value >= 0) {
                description.add(Component.text("+" + value + " " + stat.string).color(stat.color).decoration(TextDecoration.ITALIC, false));
            } else {
                description.add(Component.text("-" + value + " " + stat.string).color(stat.color).decoration(TextDecoration.ITALIC, false));
            }
        }

        description.add(Component.text(""));

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
        ItemStack newItem = new ItemStack(type.material);
        ItemMeta meta = newItem.getItemMeta();

        // modify meta
        meta.lore(createDescription());
        meta.displayName(type.itemName);

        // set item meta
        newItem.setItemMeta(meta);
        // set tags
        newItem = NBTutil.setNBT(newItem, "customItem", uuid);
        newItem = NBTutil.setNBT(newItem, "weaponType", type.toString());

        for (int i = 0; i < 25; i++) {
            newItem = NBTutil.setNBT(newItem, String.valueOf(i), components.get(i));
        }

        return newItem;
    }

    public ItemStack getCoreItem() {
        ItemStack newItem = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta meta = newItem.getItemMeta();

        // modify meta
        ArrayList<Component> lore = new ArrayList<>();

        for (Stat stat : type.baseStats.keySet()) {
            double value = type.baseStats.get(stat);

            if (value >= 0) {
                lore.add(Component.text("+" + value + " " + stat.string).color(stat.color).decoration(TextDecoration.ITALIC, false));
            } else {
                lore.add(Component.text("-" + value + " " + stat.string).color(stat.color).decoration(TextDecoration.ITALIC, false));
            }
        }

        meta.lore(lore);
        meta.displayName(Component.text("Core").decoration(TextDecoration.ITALIC, false).decoration(TextDecoration.BOLD,
                true));

        // set meta
        newItem.setItemMeta(meta);

        return newItem;
    }
}