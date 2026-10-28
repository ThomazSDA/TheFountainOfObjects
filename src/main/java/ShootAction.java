public class ShootAction implements Action {

    private final Direction direction;

    public ShootAction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(Game game) {

        Map map = game.getMap();
        Player player = game.getPlayer();

        Location playerLocation = player.getLocation();
        Location shootLocation = new Location(playerLocation.getRow() + direction.getRowOffset(),
                playerLocation.getColumn() + direction.getColumnOffset());

        if (!player.hasBow()) {
            System.out.println(Color.RED + "You do not have access to a bow in this challenge!" + Color.RESET);
            return;
        }

        if (player.getArrows() <= 0) {
            System.out.println(Color.RED + "You have no arrows left!" + Color.RESET);
            return;
        }

        if (!map.isOnMap(shootLocation) && playerLocation.equals(map.getEntranceLocation())) {
            System.out.println(Color.YELLOW + "You shoot your arrow into the light of the entrance...");
            System.out.println("You casually walk over and pick it back up." + Color.RESET);
            return;
        }

        System.out.println(Color.MAGENTA + "You loose an arrow into the darkness..." + Color.RESET);
        player.useArrow();

        if (!map.isOnMap(shootLocation)) {
            System.out.println(Color.WHITE + "CLONK! Your arrow hits a wall." + Color.RESET);
            return;
        }

        Monster hitMonster = null;

        for (Monster monster: map.getMonsters()) {
            if (monster.getLocation().equals(shootLocation)) {
                hitMonster = monster;
                break;
            }
        }

        if (hitMonster != null) {
            map.getMonsters().remove(hitMonster);
            System.out.println(Color.YELLOW + "The arrow struck home and felled the monster lurking in the next room!" + Color.RESET);
            return;
        }

        System.out.println(Color.WHITE + "There's nothing there." + Color.RESET);
    }
}
