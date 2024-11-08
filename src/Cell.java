public class Cell {
    private final boolean isTarget;
    private boolean isBlocked;
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

    public boolean isBlocked() {
        return isBlocked;
    }

    public String toString() {
        if (isBlocked()) {
            return " X "; // If the cell is blocked, print "S"
        }
        return (isTarget ? "W" : " ") + " " +
                (piece != null ? piece.getColor() : "E");
    }


    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
