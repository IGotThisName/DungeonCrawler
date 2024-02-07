package olmic.dungeoncrawler.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.EventListener;
import java.util.HashMap;

public class ProfileManager implements EventListener {

    public HashMap<Player, PlayerProfile> playerProfiles;

    public ProfileManager() {
        playerProfiles = new HashMap<Player, PlayerProfile>();
    }

    public void InitialisePlayer(Player player) {

        PlayerProfile profile = new PlayerProfile();

        // modify profile to add stats

        playerProfiles.put(player, profile);
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        InitialisePlayer(player);
    }
}
