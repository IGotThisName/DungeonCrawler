package olmic.dungeoncrawler.items.components;

import olmic.dungeoncrawler.stats.ComponentStat;

import java.util.ArrayList;
import java.util.List;

public class ComponentEffect {

    private ComponentStat stat;
    private Double value;
    private ArrayList<Direction> directions;

    ComponentEffect(ComponentStat stat, Double value, ArrayList<Direction> directions) {
        this.stat = stat;
        this.value = value;
        this.directions = directions;
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
    public List<Direction> getDirections() {
        return directions;
    }
    public void setDirections(ArrayList<Direction> directions) {
        this.directions = directions;
    }


}
