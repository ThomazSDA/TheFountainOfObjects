import java.util.Scanner;

public class Game {

    private final Map map;
    private final Player player;

    public Game(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    public void run() {

        printLocation();
        displaySenses();

        while (true) {
            playerInput();
            resolveAftermath();

            if (gameWon()) {
                System.out.println(Color.MAGENTA + "The Fountain has been reactivated and you have escaped the dungeon!" + Color.RESET);
                System.out.println(Color.MAGENTA + "You have won!" + Color.RESET);
                return;
            }

            if (!player.isAlive()) {
                System.out.println(Color.RED + player.getCauseOfDeath() + Color.RESET);
                System.out.println(Color.RED + "... Game over!" + Color.RESET);
                return;
            }
        }
    }

    public void printLocation() {
        System.out.println(Color.WHITE + "------------------------------------------------------------");

        String base = "You are in the room at " + player.getLocation();

        String arrowInfo = "";
        if (player.hasBow()) {
            int arrows = player.getArrows();
            String arrowDisplay = (arrows == 0)
                    ? Color.RED + "" + arrows
                    : Color.WHITE + "" + arrows;
            arrowInfo = " | Arrows: " + arrowDisplay;
        }
        System.out.println(base + arrowInfo + Color.RESET);
    }

    public void displaySenses() {
        Room current = map.getRoom(player.getLocation());
        String roomSense = current.getRoomSense();
        if (!roomSense.isEmpty()) System.out.println(roomSense);

        for (Direction dir: Direction.values()) {
            Location adjacent = new Location(
                    player.getLocation().getRow() + dir.getRowOffset(),
                    player.getLocation().getColumn() + dir.getColumnOffset()
            );
            if (!map.isOnMap(adjacent)) continue;

            Room room = map.getRoom(adjacent);
            String sense = room.getNearbySense();

            if (!sense.isEmpty()) {
                System.out.println(sense);
            }
        }

        for (Monster monster: map.getMonsters()) {
            if (monster.getLocation().isAdjacentTo(player.getLocation())) {
                String monsterSense = monster.getNearbySense();

                if (!monsterSense.isEmpty()) {
                    System.out.println(monsterSense);
                }
            }
        }
    }

    private void resolveAftermath() {

        boolean continueResolving;

        do {
            continueResolving = false;
            printLocation();
            displaySenses();
            Location before = player.getLocation();
            Room room = map.getRoom(before);
            room.enter(this);

            if (!player.isAlive()) return;

            for (Monster monster: map.getMonsters()) {
                if (monster.getLocation().equals(player.getLocation())) {
                    monster.activate(this);
                }
            }

            if (!player.isAlive()) return;

            if (!player.getLocation().equals(before)) {
                continueResolving = true;
            }

        } while (continueResolving);
    }

    public void playerInput() {
        Scanner scanner = new Scanner(System.in);
        Action action = null;

        while (action == null) {
            System.out.println(Color.CYAN + "What do you want to do? " + Color.RESET);
            String input = scanner.nextLine().toLowerCase();
            action = switch (input) {
                case "move north" -> new MoveAction(Direction.NORTH);
                case "move east" -> new MoveAction(Direction.EAST);
                case "move south" -> new MoveAction(Direction.SOUTH);
                case "move west" -> new MoveAction(Direction.WEST);
                case "enable fountain" -> new EnableFountainAction();
                case "map" -> new PrintMapAction();
                case "shoot north" -> new ShootAction(Direction.NORTH);
                case "shoot east" -> new ShootAction(Direction.EAST);
                case "shoot south" -> new ShootAction(Direction.SOUTH);
                case "shoot west" -> new ShootAction(Direction.WEST);
                case "help" -> new HelpAction();
                case "quit" -> new QuitAction();
                default -> null;
            };

            if (action == null) {
                System.out.println(Color.RED + "This is not a viable action!" + Color.RESET);
            }
        }

        action.execute(this);
    }

    public boolean gameWon() {
        Room currentRoom = map.getRoom(player.getLocation());
        Location fountainLocation = map.getFountainLocation();
        Room fountainRoom = map.getRoom(fountainLocation);
        return currentRoom instanceof EntranceRoom && fountainRoom.isFountainEnabled();
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public void endGame() {
        System.exit(0);
    }
}
