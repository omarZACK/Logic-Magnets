package Structure;
import java.util.Objects;

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
            return " X ";
        }
        return (isTarget ? "W" : " ") + " " +
                (piece != null ? piece.getColor() : "E");
    }

    public Cell copy() {
        Cell copy = new Cell(this.isTarget, this.row, this.col);
        if (this.piece != null) {
            copy.setPiece(this.piece.copy());
        }
        return copy;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Cell other = (Cell) obj;

        // Compare fields for equality
        return this.isTarget == other.isTarget &&
                this.isBlocked == other.isBlocked &&
                this.row == other.row &&
                this.col == other.col &&
                (this.piece == null ? other.piece == null : this.piece.equals(other.piece));
    }

    @Override
    public int hashCode() {
        // Generate hash code using key properties
        return Objects.hash(isTarget, isBlocked, row, col, piece);
    }

}
