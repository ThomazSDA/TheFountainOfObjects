public abstract class Monster {

    protected Location location;

    public Monster(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract void activate(Game game);

    public String getNearbySense() {
        return "";
    }
}
