import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameSize size = decideGameSize(scanner);
        boolean randomMode = decideGameMode(scanner);
        ChallengeType challengeType = decideGameChallenge(scanner);

        GameSetup setup = new GameSetup(size, randomMode, challengeType);
        Game game = setup.createGame();

        game.run();
    }

    public static GameSize decideGameSize(Scanner scanner) {
        GameSize size = null;

        while (size == null) {
            System.out.println(Color.CYAN + "Do you want to play a small, medium or large game?" + Color.RESET);
            String input = scanner.nextLine().toLowerCase();

            size = switch (input) {
                case "small" -> GameSize.SMALL;
                case "medium" -> GameSize.MEDIUM;
                case "large" -> GameSize.LARGE;
                default -> null;
            };

            if (size == null) {
                System.out.println(Color.RED + "Invalid command!" + Color.RESET);
            }
        }

        return size;
    }

    public static boolean decideGameMode(Scanner scanner) {

        while(true) {
            System.out.println(Color.CYAN + "Do you want to play a fixed dungeon or a random dungeon?" + Color.RESET);
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "fixed": return false;
                case "random": return true;
                default:
                    System.out.println(Color.RED + "Invalid command!" + Color.RESET);
            }
        }
    }

    public static ChallengeType decideGameChallenge(Scanner scanner) {
        ChallengeType challengeType = null;

        while (challengeType == null) {
            System.out.println(Color.CYAN + "Choose your challenge:\n1) Empty\n2) Pits\n3) Maelstrom\n4) Amarok\n5) Get armed\n6) Full" + Color.RESET);
            String input = scanner.nextLine().toLowerCase();

            challengeType = switch (input) {
                case "1" -> ChallengeType.EMPTY;
                case "2" -> ChallengeType.PITS;
                case "3" -> ChallengeType.MAELSTROM;
                case "4" -> ChallengeType.AMAROK;
                case "5" -> ChallengeType.ARMED;
                case "6" -> ChallengeType.FULL;
                default -> null;
            };

            if (challengeType == null) {
                System.out.println(Color.RED + "Invalid command!" + Color.RESET);
            }
        }

        return challengeType;
    }
}
