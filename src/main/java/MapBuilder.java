import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapBuilder {

    private final GameSize gameSize;
    private final ChallengeType challengeType;
    private Room[][] rooms;
    private List<Monster> monsters;

    public MapBuilder(GameSize gameSize, ChallengeType challengeType) {
        this.gameSize = gameSize;
        this.challengeType = challengeType;
    }

    public Map buildRandomMap() {

        int size = gameSize.getRoomAmount();
        rooms = createEmptyMap(size);
        monsters = new ArrayList<>();

        int pits = gameSize.getPits();
        int maelstroms = gameSize.getMaelstroms();
        int amaroks = gameSize.getAmaroks();

        switch (challengeType) {
            case EMPTY:
                pits = 0;
                maelstroms = 0;
                amaroks = 0;
                break;
            case PITS:
                maelstroms = 0;
                amaroks = 0;
                break;
            case MAELSTROM:
                pits = 0;
                amaroks = 0;
                break;
            case AMAROK:
                pits = 0;
                maelstroms = 0;
                break;
            case FULL:
                break;
        }

        // Entrance
        Location entrance = getRandomEntrance(size);
        rooms[entrance.getRow()][entrance.getColumn()] = new EntranceRoom();

        // Fountain
        Location fountain;
        do {
            fountain = getRandomLocation(size);
        } while (fountain.equals(entrance));
        rooms[fountain.getRow()][fountain.getColumn()] = new FountainRoom();


        // Pits
        for (int i = 0; i < pits; i++) {
            Location pitfall;
            do {
                pitfall = getRandomLocation(size);
            } while (!isEmptyRoom(pitfall));
            rooms[pitfall.getRow()][pitfall.getColumn()] = new PitFallRoom();
        }

        // Maelstroms
        for (int i = 0; i < maelstroms; i++) {
            Location maelstrom;
            do {
                maelstrom = getRandomLocation(size);
            } while (!isValidMonsterSpawn(maelstrom));
            monsters.add(new Maelstrom(maelstrom));
        }

        // Amaroks
        for (int i = 0; i < amaroks; i++) {
            Location amarok;
            do {
                amarok = getRandomLocation(size);
            } while (!isValidMonsterSpawn(amarok));
            monsters.add(new Amarok(amarok));
        }

        return new Map(rooms, monsters, entrance, fountain);
    }

    public Map buildFixedMap() {

        int size = gameSize.getRoomAmount();
        Room[][] rooms = createEmptyMap(size);
        SpecialLocations locations;
        List<Monster> monsters = new ArrayList<>();

        switch (gameSize) {
            case SMALL -> locations = buildSmallMap(rooms, monsters);
            case MEDIUM -> locations = buildMediumMap(rooms, monsters);
            case LARGE -> locations = buildLargeMap(rooms, monsters);
            default -> throw new IllegalStateException();
        }
        return new Map(rooms, monsters, locations.entrance(), locations.fountain());
    }

    private Room[][] createEmptyMap(int size) {

        Room[][] rooms = new Room[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                rooms[r][c] = new EmptyRoom();
            }
        }
        return rooms;
    }

    private SpecialLocations buildSmallMap(Room[][] rooms, List<Monster> monsters) {

        Location entrance = new Location(0,0);
        rooms[0][0] = new EntranceRoom();

        Location fountain = new Location(2,2);
        rooms[2][2] = new FountainRoom();
        rooms[3][3] = new PitFallRoom();

        monsters.add(new Maelstrom(new Location(3,1)));
        monsters.add(new Amarok(new Location(0,3)));

        return new SpecialLocations(entrance, fountain);
    }

    private SpecialLocations buildMediumMap(Room[][] rooms, List<Monster> monsters) {

        Location entrance = new Location(0,5);
        rooms[0][5] = new EntranceRoom();

        Location fountain = new Location(3,3);
        rooms[3][3] = new FountainRoom();
        rooms[1][1] = new PitFallRoom();
        rooms[4][4] = new PitFallRoom();

        monsters.add(new Maelstrom(new Location(3,1)));
        monsters.add(new Amarok(new Location(0,3)));
        monsters.add(new Amarok(new Location(5,4)));

        return new SpecialLocations(entrance, fountain);
    }

    private SpecialLocations buildLargeMap(Room[][] rooms, List<Monster> monsters) {

        Location entrance = new Location(7,7);
        rooms[7][7] = new EntranceRoom();

        Location fountain = new Location(4,4);
        rooms[4][4] = new FountainRoom();
        rooms[1][1] = new PitFallRoom();
        rooms[3][3] = new PitFallRoom();
        rooms[5][5] = new PitFallRoom();
        rooms[7][1] = new PitFallRoom();

        monsters.add(new Maelstrom(new Location(3,1)));
        monsters.add(new Maelstrom(new Location(6,4)));
        monsters.add(new Amarok(new Location(0,3)));
        monsters.add(new Amarok(new Location(2,4)));
        monsters.add(new Amarok(new Location(5,5)));

        return new SpecialLocations(entrance, fountain);
    }

    private Location getRandomEntrance(int size) {
        Random rand = new Random();

        int side = rand.nextInt(4);

        return switch (side) {
            case 0 -> new Location(0, rand.nextInt(size)); // top
            case 1 -> new Location(size - 1, rand.nextInt(size)); // bottom
            case 2 -> new Location(rand.nextInt(size), 0); // left
            default -> new Location(rand.nextInt(size), size - 1); // right
        };
    }

    private Location getRandomLocation(int size) {

        int row = (int)(Math.random() * size);
        int column = (int)(Math.random() * size);
        return new Location(row, column);
    }

    private boolean isEmptyRoom(Location location) {
        return rooms[location.getRow()][location.getColumn()] instanceof EmptyRoom;
    }

    public boolean isValidMonsterSpawn(Location location) {
        Room room = rooms[location.getRow()][location.getColumn()];

        if (room instanceof EntranceRoom) return false;
        if (room instanceof FountainRoom) return false;
        if (room instanceof PitFallRoom) return false;

        for (Monster monster: monsters) {
            if (monster.getLocation().equals(location)) return false;
        }
        return true;
    }
}
