package olmic.dungeoncrawler.items.components;

public enum Direction {
    UP("above"),
    DOWN("below"),
    LEFT("left"),
    RIGHT("right");

    public final String descName;

    Direction(String descName) {
        this.descName = descName;
    }
}
