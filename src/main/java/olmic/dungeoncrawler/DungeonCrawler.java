package olmic.dungeoncrawler;

import olmic.dungeoncrawler.commands.GiveBlankItem;
import olmic.dungeoncrawler.items.items.ItemManager;
import olmic.dungeoncrawler.items.components.ComponentManager;
import olmic.dungeoncrawler.stats.ProfileManager;
import olmic.dungeoncrawler.util.Keys;
import olmic.dungeoncrawler.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class DungeonCrawler extends JavaPlugin {

    public static Plugin plugin;

    public static ItemManager itemManager;
    public static ComponentManager componentManager;

    public Keys keys;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        ProfileManager profileManager = new ProfileManager();
        itemManager = new ItemManager(this);
        // itemManager.LoadItems();

        componentManager = new ComponentManager(this);
        componentManager.LoadComponents();

        // register commands
        getCommand("giveBlankItem").setExecutor(new GiveBlankItem());

        for (Player player : Bukkit.getOnlinePlayers()) {
            profileManager.InitialisePlayer(player);
        }

        // Utils.showComponentInv(Bukkit.getPlayer("olmic"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // itemManager.SaveItems();
        // componentManager.SaveComponents();
    }
}
