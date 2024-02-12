package olmic.dungeoncrawler.attacks;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.items.Item;
import olmic.dungeoncrawler.items.items.ItemManager;
import olmic.dungeoncrawler.items.items.WeaponType;
import olmic.dungeoncrawler.stats.PlayerProfile;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public abstract class Attacks implements Listener {
    protected Player player;

    protected int cooldown = 0;

    private DungeonCrawler dungeonCrawler;
    private WeaponType type;
    private PlayerProfile profile;

    public Attacks(WeaponType type, DungeonCrawler dungeonCrawler) {
        this.type = type;
        this.dungeonCrawler = dungeonCrawler;
    }

    @EventHandler
    public void playerClick(PlayerInteractEvent event) {
        player = event.getPlayer();
        profile = dungeonCrawler.getProfileManager().getPlayerProfiles().get(player);
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        int attack = 0;

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            attack = 0;
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            attack = 1;
        } else {
            return;
        }

        player.sendMessage("interacted");

        ItemStack mainHand;

        try {
            mainHand = player.getInventory().getItemInMainHand();
        } catch (Exception e) {
            mainHand = null;
        }

        String mainHandKey = NBTutil.getNBT(mainHand, "customItem");

        if (mainHand != null && mainHandKey != "") {
            ItemManager itemManager = dungeonCrawler.getItemManager();
            Item item = itemManager.items.get(mainHandKey);

            if (item.getType() == type) {
                if (attack == 0) {
                    LeftAttack();
                } else {
                    RightAttack();
                }
            }
        }
    }

    protected void SetCooldown(int ticks) {
        cooldown = ticks;
    }

    protected abstract void LeftAttack();
    protected abstract void RightAttack();
}
