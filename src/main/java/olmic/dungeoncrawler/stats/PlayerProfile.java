package olmic.dungeoncrawler.stats;

import java.util.HashMap;
import java.util.Map;

public class PlayerProfile {

    private HashMap<Stat, Double> stats;

    public PlayerProfile() {
        stats = new HashMap<Stat, Double>();

        for (Stat stat : Stat.values()) {
            stats.put(stat, 0d);
        }
    }

    public void addStat(Stat stat, Double value) {
        if (!stats.containsKey(stat)) {
            stats.put(stat, value);
        } else {
            double old = stats.get(stat);
            stats.put(stat, old + value);
        }
    }

    public void setStat(Stat stat, Double value) {
        stats.put(stat, value);
    }

    public double getStat(Stat stat) {
        return stats.get(stat);
    }
}