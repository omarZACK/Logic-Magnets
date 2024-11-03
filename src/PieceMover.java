import java.util.List;
import java.util.ArrayList;

public class PieceMover {
    private final GameBoard gameBoard;

    public PieceMover(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        if (!gameBoard.isValidPosition(fromRow, fromCol) || !gameBoard.isValidPosition(toRow, toCol)) {
            System.out.println("Invalid move. Position out of bounds.");
            return;
        }

        Cell fromCell = gameBoard.getCell(fromRow, fromCol);
        Cell toCell = gameBoard.getCell(toRow, toCol);

        if (toCell.isOccupied()) {
            System.out.println("Cannot move there, cell is occupied.");
            return;
        }

        Piece piece = fromCell.getPiece();
        if (piece != null && piece.isMagnetic()) {
            toCell.setPiece(piece);
            fromCell.setPiece(null);

            // Check if the moved piece is purple
            if ("P".equals(toCell.getPiece().getColor())) {
                pushAllCells(toRow, toCol);
            }
            else if ("R".equals(toCell.getPiece().getColor())) {
                pullAllCells(toRow, toCol);
            }
        } else {
            System.out.println("Invalid move. Please select a magnetic piece.");
        }
    }


    public List<int[]> getPossibleMoves() {
        List<int[]> possibleMoves = new ArrayList<>();
        int height = gameBoard.getHeight();
        int width = gameBoard.getWidth();
    
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell cell = gameBoard.getCell(row, col);
                if (cell.isOccupied() && cell.getPiece().isMagnetic()) {
                    possibleMoves.addAll(getPossibleMovesForPiece(row, col));
                }
            }
        }
    
        return possibleMoves;
    }
    
    private List<int[]> getPossibleMovesForPiece(int row, int col) {
        List<int[]> moves = new ArrayList<>();
        int height = gameBoard.getHeight();
        int width = gameBoard.getWidth();
    
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (!gameBoard.getCell(r, c).isOccupied()) {
                    moves.add(new int[]{row, col, r, c});
                }
            }
        }
    
        return moves;
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

        switch (direction) {
            case UP:
                for (int r = 0; r < row; r++) {
                    Piece piece = gameBoard.getCell(r, col).getPiece();
                    if (piece != null) {
                        gameBoard.getCell(r, col).setPiece(null);
                        gameBoard.getCell(r - 1, col).setPiece(piece);
                    }
                }
                break;

            case DOWN:
                for (int r = height - 1; r > row; r--) {
                    Piece piece = gameBoard.getCell(r, col).getPiece();
                    if (piece != null) {
                        gameBoard.getCell(r, col).setPiece(null);
                        gameBoard.getCell(r + 1, col).setPiece(piece);
                    }
                }
                break;

            case LEFT:
                for (int c = 0; c < col; c++) {
                    Piece piece = gameBoard.getCell(row, c).getPiece();
                    if (piece != null) {
                        gameBoard.getCell(row, c).setPiece(null);
                        gameBoard.getCell(row, c - 1).setPiece(piece);
                    }
                }
                break;

            case RIGHT:
                for (int c = width - 1; c > col; c--) {
                    Piece piece = gameBoard.getCell(row, c).getPiece();
                    if (piece != null) {
                        gameBoard.getCell(row, c).setPiece(null);
                        gameBoard.getCell(row, c + 1).setPiece(piece);
                    }
                }
                break;
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

        switch (direction) {
            case UP:
                for (int r = row - 1; r >= 0; r--) {
                    Piece piece = gameBoard.getCell(r, col).getPiece();
                    if (piece != null) {
                        gameBoard.getCell(r, col).setPiece(null);
                        gameBoard.getCell(r + 1, col).setPiece(piece);
                    }
                }
                break;

            case DOWN:
                for (int r = row + 1; r < height; r++) {
                    Piece piece = gameBoard.getCell(r, col).getPiece();
                    if (piece != null) {
                        gameBoard.getCell(r, col).setPiece(null);
                        gameBoard.getCell(r - 1, col).setPiece(piece);
                    }
                }
                break;

            case LEFT:
                for (int c = col - 1; c >= 0; c--) {
                    Piece piece = gameBoard.getCell(row, c).getPiece();
                    if (piece != null) {
                        gameBoard.getCell(row, c).setPiece(null);
                        gameBoard.getCell(row, c + 1).setPiece(piece);
                    }
                }
                break;

            case RIGHT:
                for (int c = col + 1; c < width; c++) {
                    Piece piece = gameBoard.getCell(row, c).getPiece();
                    if (piece != null) {
                        gameBoard.getCell(row, c).setPiece(null);
                        gameBoard.getCell(row, c - 1).setPiece(piece);
                    }
                }
                break;
        }
    }
}