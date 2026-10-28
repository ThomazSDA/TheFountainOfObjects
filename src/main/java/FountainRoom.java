public class FountainRoom extends Room {

    private boolean enabled = false;

//    public void enter(Game game) { }

    @Override
    public String getRoomSense() {
        if (enabled) {
            return Color.BLUE + "You hear the rushing waters from the Fountain of Objects." + Color.RESET;
        } else {
            return Color.BLUE + "You hear water dripping in this room. The Fountain of Objects is here!" + Color.RESET;
        }
    }

    @Override
    public void enableFountain(Game game) {

        if (enabled) {
            System.out.println(Color.PURPLE + "The fountain is already enabled!" + Color.RESET);
            return;
        }

        enabled = true;
        System.out.println(Color.BLUE + "You activated the Fountain. Water begins to flow once more..." + Color.RESET);
    }

    @Override
    public boolean isFountainEnabled() {
        return enabled;
    }

    @Override
    public boolean isSafeForMonsters() {
        return false;
    }
}
