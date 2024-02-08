package olmic.dungeoncrawler.items.components;

import olmic.dungeoncrawler.stats.ComponentStat;

import java.util.HashMap;

public class ComponentEffect {

    private ComponentStat stat;
    private Double value;

    ComponentEffect(ComponentStat stat, Double value) {
        this.stat = stat;
        this.value = value;
    }

}
