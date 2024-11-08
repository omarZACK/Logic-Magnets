record Piece(PieceColor color, boolean isMagnetic) {

    @Override
    public String toString() {
        return switch (color) {
            case PURPLE -> "P";
            case RED -> "R";
            case BLACK -> "B";
        };
    }

    public String getColor() {
        return color.toString().charAt(0)+"";
    }
}
