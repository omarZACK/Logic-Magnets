public class Cell {
    private final boolean isTarget;
    private final int row;
    private final int col;
    private Piece piece;

    public Cell(boolean isTarget, int row, int col) {
        this.isTarget = isTarget;
        this.row = row;
        this.col = col;
    }

    public boolean isTarget() {
        return isTarget;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return isOccupied() ? piece.toString() : (isTarget ? "W" : " ");
    }
}
