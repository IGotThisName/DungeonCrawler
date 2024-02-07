package olmic.dungeoncrawler;

import olmic.dungeoncrawler.stats.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class DungeonCrawler extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        ProfileManager profileManager = new ProfileManager();

        for (Player player : Bukkit.getOnlinePlayers()) {
            profileManager.InitialisePlayer(player);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
