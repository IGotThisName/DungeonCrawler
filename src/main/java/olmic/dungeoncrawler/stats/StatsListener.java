package olmic.dungeoncrawler.stats;

import olmic.dungeoncrawler.DungeonCrawler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.util.HashMap;

public class StatsListener implements Listener {

    public StatsListener(DungeonCrawler dungeonCrawler) {
        this.dungeonCrawler = dungeonCrawler;
    }

    private DungeonCrawler dungeonCrawler;

    @EventHandler
    public void swapHand(PlayerItemHeldEvent event) {
        Bukkit.getScheduler().runTaskLater(dungeonCrawler.plugin, new Runnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();

                ProfileManager profileManager = dungeonCrawler.getProfileManager();
                HashMap<Player, PlayerProfile> playerProfiles = profileManager.getPlayerProfiles();

                playerProfiles.get(player).updateStats();
            }
        }, 0);
    }
}