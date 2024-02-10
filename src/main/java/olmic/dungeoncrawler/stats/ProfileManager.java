package olmic.dungeoncrawler.stats;

import net.kyori.adventure.text.TextComponent;
import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.components.ItemComponent;
import olmic.dungeoncrawler.items.items.Item;
import olmic.dungeoncrawler.util.Keys;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;

public class ProfileManager implements Listener {

    public HashMap<Player, PlayerProfile> playerProfiles;

    public ProfileManager() {
        playerProfiles = new HashMap<Player, PlayerProfile>();
    }

    public void InitialisePlayer(Player player) {

        PlayerProfile profile = new PlayerProfile();

        // modify profile to add stats


        PlayerInventory playerInv = player.getInventory();

        HashMap<String, ItemComponent> components = DungeonCrawler.componentManager.components;
        HashMap<String, Item> items = DungeonCrawler.itemManager.items;

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
                    Material material = item.getType();
                    TextComponent textName = (TextComponent) item.getItemMeta().displayName();
                    String name = textName.content();
                    ArrayList<String> itemComponents = new ArrayList<>();

                    for (int n = 0; n < 25; n++) {
                        itemComponents.add(NBTutil.getNBT(item, String.valueOf(n)));
                    }

                    Item customItem = new Item(material, name, itemComponents, key);
                    items.put(key, customItem);

                    playerInv.setItem(i, customItem.getItemStack());
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
