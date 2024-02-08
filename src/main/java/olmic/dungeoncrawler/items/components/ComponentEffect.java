package olmic.dungeoncrawler.items.components;

import olmic.dungeoncrawler.stats.ComponentStat;

import java.util.HashMap;
import java.util.List;

public class ComponentEffect {

    private ComponentStat stat;
    private Double value;
    private List<Direction> direction;

    ComponentEffect(ComponentStat stat, Double value, List<Direction> direction) {
        this.stat = stat;
        this.value = value;
        this.direction = direction;
    }

    public ComponentStat getStat() {
        return stat;
    }
    public void setStat(ComponentStat stat) {
        this.stat = stat;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public List<Direction> getDirection() {
        return direction;
    }
    public void setDirection(List<Direction> direction) {
        this.direction = direction;
    }


}
