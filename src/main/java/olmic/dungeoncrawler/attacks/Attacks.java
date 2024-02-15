package olmic.dungeoncrawler.attacks;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.items.items.Item;
import olmic.dungeoncrawler.items.items.ItemManager;
import olmic.dungeoncrawler.items.items.WeaponType;
import olmic.dungeoncrawler.stats.PlayerProfile;
import olmic.dungeoncrawler.stats.Stat;
import olmic.dungeoncrawler.util.NBTutil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Attacks implements Listener {
    protected Player player;
    protected PlayerProfile profile;

    protected int cooldown = 0;

    private DungeonCrawler dungeonCrawler;
    private WeaponType type;

    public Attacks(WeaponType type, DungeonCrawler dungeonCrawler, PlayerProfile profile) {
        this.type = type;
        this.dungeonCrawler = dungeonCrawler;
        this.profile = profile;
        this.player = profile.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (cooldown > 0) {
                    cooldown--;
                }
            }
        }.runTaskTimer(dungeonCrawler.plugin, 0, 1);
    }

    @EventHandler
    public void playerClick(PlayerInteractEvent event) {
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
        cooldown = (int) Math.ceil(ticks * Math.pow(1.01, -profile.getStat(Stat.AttackSpeed)));
        player.sendMessage(String.valueOf(cooldown));
    }

    protected abstract void LeftAttack();
    protected abstract void RightAttack();
}
