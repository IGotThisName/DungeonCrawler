package olmic.dungeoncrawler.stats;

import java.util.HashMap;
import java.util.Map;

public class PlayerProfile {

    private HashMap<PlayerStat, Double> stats;

    public PlayerProfile() {
        stats = new HashMap<PlayerStat, Double>();

        for (PlayerStat stat : PlayerStat.values()) {
            stats.put(stat, 0d);
        }
    }

    public void addStat(PlayerStat stat, Double value) {
        double old = stats.get(stat);
        stats.put(stat, old + value);
    }

    public void setStat(PlayerStat stat, Double value) {
        stats.put(stat, value);
    }

    public double getStat(PlayerStat stat) {
        return stats.get(stat);
    }
}