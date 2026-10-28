import java.util.List;

public class Map {

    private final Room[][] rooms;
    private final List<Monster> monsters;
    private final Location entrance;
    private final Location fountain;

    public Map(Room[][] rooms, List<Monster> monsters, Location entrance, Location fountain) {
        this.rooms = rooms;
        this.monsters = monsters;
        this.entrance = entrance;
        this.fountain = fountain;
    }

    public Room getRoom(Location location) {
        return rooms[location.getRow()][location.getColumn()];
    }

    public Location getEntranceLocation() {
        return entrance;
    }

    public Location getFountainLocation() {
        return fountain;
    }

    public boolean isOnMap(Location loc) {
        return loc.getRow() >= 0 && loc.getRow() < rooms.length &&
                loc.getColumn() >= 0 && loc.getColumn() < rooms[0].length;
    }

    public void printDebugMap(Player player, List<Monster> monsters) {
        int size = rooms.length;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Location loc = new Location(r, c);

                if (player.getLocation().equals(loc)) {
                    System.out.print(Color.CYAN + "P " + Color.RESET);
                } else if (isMonsterAt(loc, monsters)) {
                    System.out.print(Color.RED + "M " + Color.RESET);
                } else if (rooms[r][c] instanceof EntranceRoom) {
                    System.out.print(Color.YELLOW + "E " + Color.RESET);
                } else if (rooms[r][c] instanceof FountainRoom) {
                    System.out.print(Color.BLUE + "F " + Color.RESET);
                } else if (rooms[r][c] instanceof PitFallRoom) {
                    System.out.print(Color.RED + "O " + Color.RESET);
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public int getSize() {
        return rooms.length;
    }

    public List<Monster> getMonsters() { return monsters; }

    private boolean isMonsterAt(Location loc, List<Monster> monsters) {
        for (Monster monster: monsters) {
            if (monster.getLocation().equals(loc)) return true;
        }
        return false;
    }
}
