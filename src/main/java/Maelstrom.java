public class Maelstrom extends Monster {

    public Maelstrom(Location location) {
        super(location);
    }

    @Override
    public void activate(Game game) {
        Player player = game.getPlayer();
        int size = game.getMap().getSize();
        System.out.println(Color.MAGENTA + "You have encountered a Maelstrom. It's strong gust of wind has swept you away to another room!" + Color.RESET);

//        System.out.println("(Debug) Old Maelstrom: " + this.getLocation());
        Location playerLocation = clamp(new Location(
                player.getLocation().getRow() - 1,
                player.getLocation().getColumn() + 2), size);
        player.setLocation(playerLocation);

        Location maelstromLocation = clamp(new Location(
                this.getLocation().getRow() + 1,
                this.getLocation().getColumn() - 2), size);
        this.setLocation(maelstromLocation);
        System.out.println(Color.MAGENTA + "The Maelstrom drifts to another part of the dungeon..." + Color.RESET);
//        System.out.println("(Debug) Maelstrom moved to: " + maelstromLocation);
    }

    @Override
    public String getNearbySense() {
        return Color.RED + "You hear the growling and groaning of a Maelstrom nearby." + Color.RESET;
    }

    private Location clamp(Location location, int size) {
        int newRow = location.getRow();
        if (newRow < 0) newRow = 0;
        if (newRow >= size) newRow = size - 1;

        int newColumn = location.getColumn();
        if (newColumn < 0) newColumn = 0;
        if (newColumn >= size) newColumn = size - 1;

        return new Location(newRow, newColumn);
    }
}
