import java.util.List;

public class PrintMapAction implements Action {

    @Override
    public void execute(Game game) {
        Player player = game.getPlayer();
        List<Monster> monsters = game.getMap().getMonsters();
        game.getMap().printDebugMap(player, monsters);
    }
}
