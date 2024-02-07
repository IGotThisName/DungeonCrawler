package olmic.dungeoncrawler.items;


import olmic.dungeoncrawler.DungeonCrawler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ItemManager {

    public HashMap<String, Item> items;
    private DungeonCrawler dungeonCrawler;

    private File configFile;
    private FileConfiguration config;

    public ItemManager(DungeonCrawler dungeonCrawler) {
        this.dungeonCrawler = dungeonCrawler;
        items = new HashMap<String, Item>();
    }

    public static void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadItems() {

        configFile = new File(dungeonCrawler.getDataFolder()+"/items.yml");
        config = YamlConfiguration.loadConfiguration(configFile);

        for (String key : config.getKeys(false)) {



        }

    }

    public void SaveItems() {
        for (String key : items.keySet()) {

        }

        config.set("test", "test2");

        saveCustomYml(config, configFile);
    }
}
