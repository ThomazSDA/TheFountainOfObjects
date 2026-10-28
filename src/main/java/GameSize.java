public enum GameSize {
    SMALL(4,1,1,1),
    MEDIUM(6,2,1,2),
    LARGE(8,4,2,3);

    private final int roomAmount;
    private final int pits;
    private final int maelstroms;
    private final int amaroks;

    GameSize(int roomAmount, int pits, int maelstroms, int amaroks) {
        this.roomAmount = roomAmount;
        this.pits = pits;
        this.maelstroms = maelstroms;
        this.amaroks = amaroks;
    }

    public int getRoomAmount() {
        return roomAmount;
    }

    public int getPits() {
        return pits;
    }

    public int getMaelstroms() {
        return maelstroms;
    }

    public int getAmaroks() { return amaroks; }
}