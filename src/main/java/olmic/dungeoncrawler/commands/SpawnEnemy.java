package olmic.dungeoncrawler.commands;

import olmic.dungeoncrawler.DungeonCrawler;
import olmic.dungeoncrawler.enemies.enemyClasses.DormantGolem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnEnemy implements CommandExecutor {

    private DungeonCrawler dungeonCrawler;

    public SpawnEnemy(DungeonCrawler dungeonCrawler) {
        this.dungeonCrawler = dungeonCrawler;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        DormantGolem enemy = new DormantGolem(player.getLocation(), dungeonCrawler);
        dungeonCrawler.getEnemyManager().CreateEnemy(enemy);

        return false;
    }
}
