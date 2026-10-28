public abstract class Room {

    public void enter(Game game) {
    }

    public String getRoomSense() {
        return "";
    }

    public String getNearbySense() {
        return "";
    }

    public void enableFountain(Game game) {
        System.out.println(Color.BLUE + "The fountain is not in this room. There was no effect." + Color.RESET);
    }

    public boolean isFountainEnabled() {
        return false;
    }

    public boolean isSafeForMonsters() {
        return true;
    }
}
