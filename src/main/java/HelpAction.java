public class HelpAction implements Action {

    @Override
    public void execute(Game game) {
        System.out.println(Color.WHITE + "------------------------------------------------------------");
        System.out.println(Color.CYAN + "You can perform the following actions:");
        System.out.println(Color.MAGENTA + "move [direction]" + Color.RESET + " - Move north, east, south or west.");
        System.out.println(Color.MAGENTA + "enable fountain" + Color.RESET + " - Activate the fountain if you are in it's room.");
        System.out.println(Color.MAGENTA + "shoot [direction]" + Color.RESET + " - Shoot an arrow in a direction (if you have any arrows left).");
        System.out.println(Color.MAGENTA + "quit" + Color.RESET + " - Exit the game immediately");
        System.out.println(Color.MAGENTA + "help" + Color.RESET + " - Show this list.");
    }
}
