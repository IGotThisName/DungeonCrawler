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

    public static Direction getOppositeDirection(Direction direction) {
        switch (direction) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case RIGHT:
                return LEFT;
            case LEFT:
                return RIGHT;
            default:
                return null;
        }
    }
}
