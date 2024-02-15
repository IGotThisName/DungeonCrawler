package olmic.dungeoncrawler;

import olmic.dungeoncrawler.commands.GiveBlankItem;
import olmic.dungeoncrawler.commands.OpenEditItemGUI;
import olmic.dungeoncrawler.enemies.EnemyManager;
import olmic.dungeoncrawler.items.ItemEditor;
import olmic.dungeoncrawler.items.items.ItemManager;
import olmic.dungeoncrawler.items.components.ComponentManager;
import olmic.dungeoncrawler.stats.ProfileManager;
import olmic.dungeoncrawler.stats.StatsListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class DungeonCrawler extends JavaPlugin {

    public Plugin plugin;

    private ItemManager itemManager;
    private ComponentManager componentManager;
    private ProfileManager profileManager;
    private EnemyManager enemyManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        itemManager = new ItemManager(this);
        profileManager = new ProfileManager(this);
        enemyManager = new EnemyManager(this);


        // itemManager.LoadItems();

        componentManager = new ComponentManager(this);
        componentManager.LoadComponents();

        // register commands
        getCommand("giveBlankItem").setExecutor(new GiveBlankItem(this));
        getCommand("openEditItemGUI").setExecutor(new OpenEditItemGUI(this));

        // register events
        getServer().getPluginManager().registerEvents(new ItemEditor(this), this);
        getServer().getPluginManager().registerEvents(profileManager, this);
        getServer().getPluginManager().registerEvents(new StatsListener(this), this);

        // attack events


        for (Player player : Bukkit.getOnlinePlayers()) {
            profileManager.InitialisePlayer(player);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // itemManager.SaveItems();
        // componentManager.SaveComponents();
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public ComponentManager getComponentManager() {
        return componentManager;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }
}
