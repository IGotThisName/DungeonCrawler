package olmic.dungeoncrawler.items.components;

import olmic.dungeoncrawler.stats.Stat;

import java.util.ArrayList;
import java.util.List;

public class ComponentEffect {

    private Stat stat;
    private Double value;
    private ArrayList<Direction> directions;

    private Operation operation;

    ComponentEffect(Stat stat, Double value, ArrayList<Direction> directions, Operation operation) {
        this.stat = stat;
        this.value = value;
        this.directions = directions;
        this.operation = operation;
    }

    public Stat getStat() {
        return stat;
    }
    public void setStat(Stat stat) {
        this.stat = stat;
    }
    public Double getValue() {
        return value;
    }
    public String getTextValue() {

        String end = "";

        if (operation == Operation.ADD) {
            end += value.toString();
        } else {
            end += value * 100 + "%";
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
    public Operation getOperation() {
        return operation;
    }


}
