package olmic.dungeoncrawler.stats;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.components.ItemComponent;
import olmic.dungeoncrawler.items.items.Item;
import olmic.dungeoncrawler.items.items.WeaponType;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileManager implements Listener {

    private HashMap<Player, PlayerProfile> playerProfiles;

    public ProfileManager(DungeonCrawler dungeonCrawler) {
        playerProfiles = new HashMap<Player, PlayerProfile>();
        this.dungeonCrawler = dungeonCrawler;
    }

    private DungeonCrawler dungeonCrawler;

    public void InitialisePlayer(Player player) {

        PlayerProfile profile = new PlayerProfile(player, dungeonCrawler);

        // modify profile to add stats

        PlayerInventory playerInv = player.getInventory();

        HashMap<String, ItemComponent> components = dungeonCrawler.getComponentManager().components;
        HashMap<String, Item> items = dungeonCrawler.getItemManager().items;

        for (int i = 0; i < playerInv.getSize(); i++) {

            if (playerInv.getItem(i) != null) {
                ItemStack item = playerInv.getItem(i);
                ItemMeta itemMeta = item.getItemMeta();

                // components
                for (String key : components.keySet()) {
                    try {
                        if (NBTutil.getNBT(item, "component").equals(key)) {

                            playerInv.setItem(i, components.get(key).getItemStack());

                            Bukkit.getLogger().info("FOUND/UPDATED COMPONENT");

                        }
                    } catch(NullPointerException e) {
                    }
                }

                // items
                if (NBTutil.getNBT(item, "customItem") != "") {
                    Bukkit.getLogger().info("FOUND/UPDATED ITEM");

                    String key = NBTutil.getNBT(item, "customItem");
                    ArrayList<String> itemComponents = new ArrayList<>();

                    for (int n = 0; n < 25; n++) {
                        itemComponents.add(NBTutil.getNBT(item, String.valueOf(n)));
                    }

                    WeaponType type = WeaponType.valueOf(NBTutil.getNBT(item, "weaponType"));

                    Item customItem = new Item(type, itemComponents, key, dungeonCrawler);
                    items.put(key, customItem);

                    playerInv.setItem(i, customItem.getItemStack());
                }
            }
        }

        profile.updateStats();
        playerProfiles.put(player, profile);
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        InitialisePlayer(player);
    }

    @EventHandler
    public void PlayerLeaveEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        playerProfiles.remove(player);
    }

    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        playerProfiles.get(player).ResetHealth();
    }

    public HashMap<Player, PlayerProfile> getPlayerProfiles() {
        return playerProfiles;
    }
}
