package olmic.dungeoncrawler.items.components;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.stats.Stat;
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

    public HashMap<String, ItemComponent> components;
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
                Stat stat = Stat.valueOf(config.getString(effectPath + ".stat"));
                // effect value
                Double value = config.getDouble(effectPath + ".value");
                // effect operation
                Operation operation = Operation.valueOf(config.getString(effectPath + ".operation"));
                // get directions
                List<String> directionsStrings = config.getStringList(effectPath + ".direction");
                ArrayList<Direction> directions = new ArrayList<>();

                for (int i = 0; i < directionsStrings.size(); i++) {
                    directions.add(Direction.valueOf(directionsStrings.get(i)));
                }

                // build and add effect
                ComponentEffect effect = new ComponentEffect(stat, value, directions, operation);
                effects.add(effect);
            }

            ItemComponent itemComponent = new ItemComponent(material, name, effects, key);

            components.put(key, itemComponent);
            Bukkit.getLogger().info("Loaded Component");
        }
    }

    public void SaveComponents() {
        for (String key : components.keySet()) {
            ItemComponent itemComponent = components.get(key);

            // for each component
        }

        saveCustomYml(config, configFile);
    }
}
