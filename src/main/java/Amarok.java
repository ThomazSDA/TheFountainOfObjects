public class Amarok extends Monster {

    public Amarok(Location location) {
        super(location);
    }

    @Override
    public void activate(Game game) {
        Player player = game.getPlayer();
        System.out.println(Color.RED + "You stepped in a room with an Amarok and didn't notice the monster until it was far too late." + Color.RESET);
        player.kill("You were devoured by the Amarok!");
    }

    @Override
    public String getNearbySense() {
        return Color.RED + "You can smell the rotten stench of an Amarok in a nearby room." + Color.RESET;
    }
}
