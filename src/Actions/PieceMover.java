package Actions;
import Structure.Cell;
import Structure.GameBoard;
import Structure.Piece;
import java.util.*;

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
            this.gameBoard.cellsWithPieces.remove(fromCell);
            this.gameBoard.cellsWithPieces.add(toCell);
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
        for (Directions direction : Directions.values()) {
            pushInDirection(direction, row, col);
        }
    }

    private void pushInDirection(Directions direction, int row, int col) {
        int height = gameBoard.getHeight();
        int width = gameBoard.getWidth();
        int dRow = 0, dCol = 0;

        switch (direction) {
            case UP -> dRow = -1;
            case DOWN -> dRow = 1;
            case LEFT -> dCol = -1;
            case RIGHT -> dCol = 1;
        }

        List<Cell> cellsToPush = new ArrayList<>();
        int currentRow = row + dRow;
        int currentCol = col + dCol;
        boolean pushingGroup = false;

        while (currentRow >= 0 && currentRow < height && currentCol >= 0 && currentCol < width) {
            Cell currentCell = gameBoard.getCell(currentRow, currentCol);

            if (currentCell == null || currentCell.isBlocked()) {
                currentRow += dRow;
                currentCol += dCol;
                continue;
            }

            if (currentCell.getPiece() != null) {
                pushingGroup = true;
                cellsToPush.add(currentCell);
            } else if (pushingGroup) {
                break;
            }

            currentRow += dRow;
            currentCol += dCol;
        }

        if (!cellsToPush.isEmpty()) {
            for (int i = cellsToPush.size() - 1; i >= 0; i--) {
                Cell currentCell = cellsToPush.get(i);
                int targetRow = currentCell.getRow() + dRow;
                int targetCol = currentCell.getCol() + dCol;

                if (targetRow >= 0 && targetRow < height && targetCol >= 0 && targetCol < width) {
                    Cell targetCell = gameBoard.getCell(targetRow, targetCol);

                    if (targetCell != null && !targetCell.isBlocked() && !targetCell.isOccupied()) {
                        targetCell.setPiece(currentCell.getPiece());
                        currentCell.setPiece(null);
                        this.gameBoard.cellsWithPieces.add(targetCell);
                        this.gameBoard.cellsWithPieces.remove(currentCell);
                    }
                }
            }
        }
    }

    private void pullAllCells(int row, int col) {
        for (Directions direction : Directions.values()) {
            pullInDirection(direction, row, col);
        }
    }

    private void pullInDirection(Directions direction, int row, int col) {
        int height = gameBoard.getHeight();
        int width = gameBoard.getWidth();
        int dRow = 0, dCol = 0;

        switch (direction) {
            case UP -> dRow = -1;
            case DOWN -> dRow = 1;
            case LEFT -> dCol = -1;
            case RIGHT -> dCol = 1;
        }

        int currentRow = row + dRow;
        int currentCol = col + dCol;

        while (currentRow >= 0 && currentRow < height && currentCol >= 0 && currentCol < width) {
            Cell currentCell = gameBoard.getCell(currentRow, currentCol);

            if (currentCell == null || currentCell.isBlocked()) {
                currentRow += dRow;
                currentCol += dCol;
                continue;
            }

            if (currentCell.getPiece() != null) {
                Cell previousCell = gameBoard.getCell(currentRow - dRow, currentCol - dCol);
                if (previousCell != null && !previousCell.isOccupied()) {
                    previousCell.setPiece(currentCell.getPiece());
                    currentCell.setPiece(null);
                    this.gameBoard.cellsWithPieces.add(previousCell);
                    this.gameBoard.cellsWithPieces.remove(currentCell);
                }
            }
            currentRow += dRow;
            currentCol += dCol;
        }
    }
}
