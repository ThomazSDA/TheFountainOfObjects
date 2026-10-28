public class EnableFountainAction implements Action {

    @Override
    public void execute(Game game) {
        Room room = game.getMap().getRoom(game.getPlayer().getLocation());

        room.enableFountain(game);
    }
}
