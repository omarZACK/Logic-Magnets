import java.util.ArrayList;
import java.util.List;

public class PieceMover {
    private final GameBoard gameBoard;

    public PieceMover(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {

        Cell fromCell = gameBoard.getCell(fromRow, fromCol);
        Cell toCell = gameBoard.getCell(toRow, toCol);

        if (toCell == null || toCell.isOccupied() || toCell.isBlocked()) {
            System.out.println("Cannot move there, cell is blocked or occupied.");
            return;
        }

        Piece piece = fromCell.getPiece();
        if (piece != null && piece.isMagnetic()) {
            toCell.setPiece(piece);
            fromCell.setPiece(null);

            // Check if the moved piece is purple or red and apply corresponding effects
            if ("P".equals(piece.getColor())) {
                pushAllCells(toRow, toCol);
            } else if ("R".equals(piece.getColor())) {
                pullAllCells(toRow, toCol);
            }
        } else {
            System.out.println("Invalid move. Please select a magnetic piece.");
        }
    }

    private void pushAllCells(int row, int col) {
        pushInDirection(Directions.UP, row, col);
        pushInDirection(Directions.DOWN, row, col);
        pushInDirection(Directions.LEFT, row, col);
        pushInDirection(Directions.RIGHT, row, col);
    }

    private void pushInDirection(Directions direction, int row, int col) {
        int height = gameBoard.getHeight();
        int width = gameBoard.getWidth();
        int dRow = 0, dCol = 0;

        switch (direction) {
            case UP: dRow = -1; break;
            case DOWN: dRow = 1; break;
            case LEFT: dCol = -1; break;
            case RIGHT: dCol = 1; break;
        }

        List<Cell> cellsToPush = new ArrayList<>();
        int currentRow = row + dRow;
        int currentCol = col + dCol;
        boolean pushingGroup = false; // Flag to track if we're pushing a group

        // Loop to find the first piece or group to push
        while (currentRow >= 0 && currentRow < height && currentCol >= 0 && currentCol < width) {
            Cell currentCell = gameBoard.getCell(currentRow, currentCol);

            // Skip blocked cells
            if (currentCell == null || currentCell.isBlocked()) {
                currentRow += dRow;
                currentCol += dCol;
                continue;
            }

            // If the cell contains a piece, it's part of a group to push
            if (currentCell.getPiece() != null) {
                if (!pushingGroup) {
                    // Start a new group of pieces to push
                    pushingGroup = true;
                }
                cellsToPush.add(currentCell);
            } else if (pushingGroup) {
                // If we've already found a group and encounter an empty space, stop
                break;
            }

            // Move in the direction
            currentRow += dRow;
            currentCol += dCol;
        }

        // Now that we've identified the group of cells to push, move them
        if (!cellsToPush.isEmpty()) {
            // Loop through the list of cellsToPush, shifting each piece one step in the direction
            for (int i = cellsToPush.size() - 1; i >= 0; i--) {
                Cell currentCell = cellsToPush.get(i);

                // Find the cell that this piece should move to
                int targetRow = currentCell.getRow() + dRow;
                int targetCol = currentCell.getCol() + dCol;

                // Check if the target cell is within bounds and not blocked or occupied
                if (targetRow >= 0 && targetRow < height && targetCol >= 0 && targetCol < width) {
                    Cell targetCell = gameBoard.getCell(targetRow, targetCol);

                    // Move the piece to the target cell if it's not blocked and not occupied
                    if (targetCell != null && !targetCell.isBlocked() && !targetCell.isOccupied()) {
                        targetCell.setPiece(currentCell.getPiece());
                        currentCell.setPiece(null); // Set the original cell to null
                    }
                }
            }
        }
    }
    private void pullAllCells(int row, int col) {
        pullInDirection(Directions.UP, row, col);
        pullInDirection(Directions.DOWN, row, col);
        pullInDirection(Directions.LEFT, row, col);
        pullInDirection(Directions.RIGHT, row, col);
    }

    private void pullInDirection(Directions direction, int row, int col) {
        int height = gameBoard.getHeight();
        int width = gameBoard.getWidth();
        int dRow = 0, dCol = 0;

        switch (direction) {
            case UP: dRow = -1; break;
            case DOWN: dRow = 1; break;
            case LEFT: dCol = -1; break;
            case RIGHT: dCol = 1; break;
        }

        int currentRow = row + dRow;
        int currentCol = col + dCol;

        while (currentRow >= 0 && currentRow < height && currentCol >= 0 && currentCol < width) {
            Cell currentCell = gameBoard.getCell(currentRow, currentCol);

            // Skip blocked cells
            if (currentCell == null || currentCell.isBlocked()) {
                currentRow += dRow;
                currentCol += dCol;
                continue;
            }

            if (currentCell.getPiece() != null) {
                // Pull the piece to the previous cell if it's not blocked and the previous cell is empty
                Cell previousCell = gameBoard.getCell(currentRow - dRow, currentCol - dCol);
                if (previousCell != null && !previousCell.isOccupied()) {
                    previousCell.setPiece(currentCell.getPiece());
                    currentCell.setPiece(null);
                }
            }
            currentRow += dRow;
            currentCol += dCol;
        }
    }
}
