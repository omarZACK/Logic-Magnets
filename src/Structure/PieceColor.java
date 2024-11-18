package Structure;

public enum PieceColor {
    PURPLE("P"), RED("R"), BLACK("B");

    private final String symbol;

    PieceColor(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
