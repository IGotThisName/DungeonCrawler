package olmic.dungeoncrawler.items.components;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.Item;
import olmic.dungeoncrawler.stats.PlayerStat;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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

        configFile = new File(dungeonCrawler.getDataFolder()+"/items.yml");
        config = YamlConfiguration.loadConfiguration(configFile);

        for (String key : config.getKeys(false)) {

            // for each component

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
