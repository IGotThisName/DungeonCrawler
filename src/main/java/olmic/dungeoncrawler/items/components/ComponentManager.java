package olmic.dungeoncrawler.items.components;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.stats.ComponentStat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComponentManager {

    public HashMap<String, Component> components;
    private DungeonCrawler dungeonCrawler;

    private File configFile;
    private FileConfiguration config;

    public ComponentManager(DungeonCrawler dungeonCrawler) {
        this.dungeonCrawler = dungeonCrawler;
        components = new HashMap<>();
    }

    private static void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadComponents() {

        configFile = new File(dungeonCrawler.getDataFolder()+"/components.yml");
        config = YamlConfiguration.loadConfiguration(configFile);

        for (String key : config.getKeys(false)) {
            // get name
            String name = config.getString(key + ".name");
            // get material
            Material material = Material.getMaterial(config.getString(key + ".material"));
            // load component effects
            List<ComponentEffect> effects = new ArrayList<>();

            for (String effectKey : config.getConfigurationSection(key + ".effects").getKeys(false)) {
                String effectPath = key + ".effects." + effectKey;

                // effect stat
                ComponentStat stat = ComponentStat.valueOf(config.getString(effectPath + ".stat"));
                // effect value
                Double value = config.getDouble(effectPath + ".value");
                // get directions
                List<String> directionsStrings = config.getStringList(effectPath + ".direction");
                ArrayList<Direction> directions = new ArrayList<>();

                for (int i = 0; i < directionsStrings.size(); i++) {
                    directions.add(Direction.valueOf(directionsStrings.get(i)));
                }

                // build and add effect
                ComponentEffect effect = new ComponentEffect(stat, value, directions);
                effects.add(effect);

                Bukkit.getLogger().info("Loaded Effect in Component: " + key);
            }

            Component component = new Component(material, name, effects);

            List<String> lore = component.buildDescription();

            for (int i = 0; i < lore.size(); i++) {

                Bukkit.getLogger().info(lore.get(i));

            }

            components.put(key, component);
            Bukkit.getLogger().info("Loaded Component");
        }
    }

    public void SaveComponents() {
        for (String key : components.keySet()) {
            Component component = components.get(key);

            // for each component
        }

        saveCustomYml(config, configFile);
    }
}
