package olmic.dungeoncrawler.items.components;

import olmic.dungeoncrawler.stats.ComponentStat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComponentEffect {

    private ComponentStat stat;
    private Double value;
    private ArrayList<Direction> directions;

    private List<ComponentStat> multiStats = Arrays.asList(new ComponentStat[] {
            ComponentStat.CooldownMulti,
            ComponentStat.MagicDamageMulti,
            ComponentStat.MeleeDamageMulti,
            ComponentStat.RangedDamageMulti
    });

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
    public String getTextValue() {

        String end = "";

        if (multiStats.contains(stat)) {
            end += value * 100 + "%";
        } else {
            end += value.toString();
        }

        if (value >= 0) {
            end = "+" + end;
        }

        return end;
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
