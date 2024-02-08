package olmic.dungeoncrawler.stats;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.components.ItemComponent;
import olmic.dungeoncrawler.util.Keys;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

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


        PlayerInventory playerInv = player.getInventory();

        HashMap<String, ItemComponent> components = DungeonCrawler.componentManager.components;

        for (int i = 0; i < playerInv.getSize(); i++) {

            Bukkit.getLogger().info("looping");

            if (playerInv.getItem(i) != null) {
                ItemStack item = playerInv.getItem(i);
                ItemMeta itemMeta = item.getItemMeta();

                for (String key : components.keySet()) {
                    try {

                        Bukkit.getLogger().info("testing item");

                        if (itemMeta.getPersistentDataContainer().get(Keys.componentKey, PersistentDataType.STRING).equalsIgnoreCase(key)) {

                            playerInv.setItem(i, components.get(key).getItemStack());

                            Bukkit.getLogger().info("FOUND/UPDATED ITEM");

                        }
                    } catch(NullPointerException e) {
                        Bukkit.getLogger().info("CAUGHT");
                    }
                }
            }
        }
        
        playerProfiles.put(player, profile);
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        InitialisePlayer(player);
    }
}
