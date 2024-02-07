package olmic.dungeoncrawler;

import olmic.dungeoncrawler.items.ItemManager;
import olmic.dungeoncrawler.stats.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class DungeonCrawler extends JavaPlugin {

    public static Plugin plugin;

    private ItemManager itemManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        ProfileManager profileManager = new ProfileManager();
        itemManager = new ItemManager(this);
        itemManager.LoadItems();

        for (Player player : Bukkit.getOnlinePlayers()) {
            profileManager.InitialisePlayer(player);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        itemManager.SaveItems();
    }
}
