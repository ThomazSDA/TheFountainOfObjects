public class EntranceRoom extends Room {

//    public void enter(Game game) { }

    @Override
    public String getRoomSense() {
        return Color.YELLOW + "You see light in this room coming from outside the cavern. This is the entrance." + Color.RESET;
    }

    @Override
    public boolean isSafeForMonsters() {
        return false;
    }
}
