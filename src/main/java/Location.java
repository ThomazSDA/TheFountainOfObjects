public class Location {

    private int row, column;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() { return "(Row=" + row + ", Column=" + column + ")"; }

    public boolean isAdjacentTo(Location location) {
        int rowDiff = Math.abs(this.row - location.row);
        int columnDiff = Math.abs(this.column - location.column);

        return rowDiff <= 1 && columnDiff <= 1 && !(rowDiff == 0 && columnDiff == 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj == null) || obj.getClass() != getClass()) return false;

        Location other = (Location) obj;
        return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() {
        return 31 * row + column;
    }
}
