public class GameSetup {

    private final GameSize gameSize;
    private final boolean randomMode;
    private final ChallengeType challengeType;

    public GameSetup(GameSize gameSize, boolean randomMode, ChallengeType challengeType) {
        this.gameSize = gameSize;
        this.randomMode = randomMode;
        this.challengeType = challengeType;
    }

    public Game createGame() {

        Map map;
        MapBuilder builder = new MapBuilder(gameSize, challengeType);

        if (randomMode) {
            map = builder.buildRandomMap();
        } else {
            map = builder.buildFixedMap();
        }

        int arrows = 0;
        boolean hasBow = false;
        if (challengeType == ChallengeType.ARMED || challengeType == ChallengeType.FULL) {
            arrows = 5;
            hasBow = true;

        }

        Player player = new Player(map.getEntranceLocation(), arrows, hasBow);

        boolean showPits = (challengeType == ChallengeType.PITS || challengeType == ChallengeType.FULL);
        boolean showMaelstroms = challengeType == ChallengeType.MAELSTROM || challengeType == ChallengeType.ARMED || challengeType == ChallengeType.FULL;
        boolean showAmaroks = challengeType == ChallengeType.AMAROK || challengeType == ChallengeType.ARMED || challengeType == ChallengeType.FULL;
        boolean showArrows = challengeType == ChallengeType.ARMED || challengeType == ChallengeType.FULL;

        System.out.println();
        System.out.println(Color.CYAN + "You enter the Cavern of Objects, a maze of rooms filled with dangerous pits in search of the Fountain of Objects.");
        System.out.println("Light is visible only in the entrance, and no other light is seen anywhere in the caverns. You must navigate the Caverns with your other senses.");
        System.out.println("Find the Fountain of Objects, activate it, and return to the entrance.");
        if (showPits) {
            pause(800);
            System.out.println("Look out for pits. You will feel a breeze if a pit is in an adjacent room. If you enter a room with a pit, you will die.");
        }
        if (showMaelstroms) {
            pause(800);
            System.out.println("Maelstroms are violent forces of sentient wind. Entering a room with one could transport you to any other location in the caverns.\nYou will be able to hear their growling and groaning in nearby rooms.");
        }
        if (showAmaroks) {
            pause(800);
            System.out.println("Amaroks roam the caverns. Encountering one is certain death, but you can smell their rotten stench in nearby rooms.");
        }
        if (showArrows) {
            pause(800);
            System.out.println("You carry with you a bow and a quiver of arrows. You can use them to shoot monsters in the caverns, but be warned: you have a limited supply.");
        }
        System.out.println("Typing the command 'help' will display all available commands.");
        System.out.println("Good luck and may your journey be a safe one!" + Color.RESET);

        return new Game(map, player);
    }

    private void pause(int millis) {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}