public enum Direction {
    NORTH (-1,0),
    EAST(0,1),
    SOUTH(1,0),
    WEST(0,-1);

    private final int rowOffset;
    private final int columnOffset;

    Direction(int rowOffset, int columnOffset) {
        this.rowOffset = rowOffset;
        this.columnOffset = columnOffset;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public int getColumnOffset() {
        return columnOffset;
    }
}
