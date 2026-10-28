public class QuitAction implements Action {

    @Override
    public void execute(Game game) {
        game.endGame();
    }
}
