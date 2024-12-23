package Structure;

public record Piece(PieceColor color, boolean isMagnetic) {

    @Override
    public String toString() {
        return switch (color) {
            case PURPLE, BLACK, RED -> color.getSymbol();
        };
    }

    public String getColor() {
        return color.toString().charAt(0)+"";
    }

    public Piece copy() {
        return new Piece(this.color, this.isMagnetic);
    }

}
