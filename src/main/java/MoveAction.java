import java.util.Scanner;

public class MoveAction implements Action {

    private final Direction direction;

    public MoveAction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(Game game) {

        Map map = game.getMap();
        Player player = game.getPlayer();
        Location currentLocation = player.getLocation();
        Location newLocation = new Location(currentLocation.getRow() + direction.getRowOffset(),
                currentLocation.getColumn() + direction.getColumnOffset());

        if (!map.isOnMap(newLocation) && player.getLocation().equals(map.getEntranceLocation())) {
            System.out.print(Color.CYAN + "Do you want to leave the dungeon? (yes/no) ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("yes")) {
                System.out.println(Color.MAGENTA + "You left the dungeon, never to be heard again, too shameful to show face...");
                game.endGame();
            } else {
                System.out.println(Color.CYAN + "You decided to stay a little longer..." + Color.RESET);
            }
            return;
        }

        if (!map.isOnMap(newLocation)) {
            System.out.println(Color.PURPLE + "There's a wall here. BONK!" + Color.RESET);
            return;
        }

        player.setLocation(newLocation);
        System.out.println(Color.CYAN + "You moved " + direction.name().toLowerCase() + "." + Color.RESET);

        Room room = map.getRoom(newLocation);
        room.enter(game);
    }
}
