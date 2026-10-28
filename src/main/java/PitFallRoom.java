public class PitFallRoom extends Room {

    @Override
    public void enter(Game game) {
        Player player = game.getPlayer();
        player.kill("You fall in a bottomless pit!");
    }

    @Override
    public String getRoomSense() {
        return Color.RED + "You try to put your foot down, but there's no floor here!" + Color.RESET;
    }

    @Override
    public String getNearbySense() {
        return Color.RED + "You feel a draft. There is a pit in a nearby room." + Color.RESET;
    }

    @Override
    public boolean isSafeForMonsters() {
        return false;
    }
}
