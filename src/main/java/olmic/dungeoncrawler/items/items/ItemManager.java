package olmic.dungeoncrawler.items.items;


import olmic.dungeoncrawler.DungeonCrawler;

import java.util.HashMap;

public class ItemManager {

    public HashMap<String, Item> items;
    private DungeonCrawler dungeonCrawler;

    // private File configFile;
    // private FileConfiguration config;



    public ItemManager(DungeonCrawler dungeonCrawler) {
        this.dungeonCrawler = dungeonCrawler;
        items = new HashMap<>();
    }

    /* private static void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
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
            // item type (required)
            Material type = (Material) config.get(key + ".type");

            // item name (required)
            String name = (String) config.get(key + ".name");

            // load stats
            HashMap<PlayerStat, Double> stats = new HashMap<PlayerStat, Double>();
            for (PlayerStat stat : PlayerStat.values()) {

                String statPath = key + "." + stat.toString();

                if (config.contains(statPath)) {
                    stats.put(stat, config.getDouble(statPath));
                }
            }

            Item item = new Item(type, name, stats);

            items.put(key, item);
        }
    }

    public void SaveItems() {
        for (String key : items.keySet()) {
            Item item = items.get(key);

            // set type
            config.set(key + ".type", item.getMaterial());

            // set name
            config.set(key + ".name", item.getName());

            // set stats
            for (PlayerStat stat : item.getStats().keySet()) {
                config.set(key + "." + stat.toString(), item.getStats().get(stat));
            }
        }

        saveCustomYml(config, configFile);
    } */
}
