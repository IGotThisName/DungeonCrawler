package olmic.dungeoncrawler;

import olmic.dungeoncrawler.items.items.ItemManager;
import olmic.dungeoncrawler.items.components.ComponentManager;
import olmic.dungeoncrawler.stats.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class DungeonCrawler extends JavaPlugin {

    public static Plugin plugin;

    private ItemManager itemManager;
    private ComponentManager componentManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        ProfileManager profileManager = new ProfileManager();
        itemManager = new ItemManager(this);
        itemManager.LoadItems();

        componentManager = new ComponentManager(this);
        componentManager.LoadComponents();

        for (Player player : Bukkit.getOnlinePlayers()) {
            profileManager.InitialisePlayer(player);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        itemManager.SaveItems();
        componentManager.SaveComponents();
    }
}
