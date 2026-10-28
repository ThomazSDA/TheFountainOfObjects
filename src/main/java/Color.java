public enum Color {
    MAGENTA("\u001B[35m"),
    WHITE("\u001B[37m"),
    CYAN("\u001B[36m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    RED("\u001B[31m"),
    PURPLE("\u001B[35m"),
    RESET("\u001B[0m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
