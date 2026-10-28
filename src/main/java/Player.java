public class Player {

    private Location location;
    private int arrows;
    private boolean hasBow;
    private boolean isAlive = true;
    private String causeOfDeath;

    public Player(Location location, int arrows, boolean hasBow) {
        this.location = location;
        this.arrows = arrows;
        this.hasBow = hasBow;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean hasBow() {
        return hasBow;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void kill(String cause) {
        this.isAlive = false;
        this.causeOfDeath = cause;
    }

    public int getArrows() {
        return arrows;
    }

    public boolean useArrow() {
        if (!hasBow || arrows <= 0) return false;
        arrows--;
        return true;
    }
}
