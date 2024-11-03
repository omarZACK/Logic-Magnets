import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameBoard {
    private final List<Cell> cells;
    private int width;
    private int height;

    public GameBoard() {
        cells = new ArrayList<>();
    }

    public void loadFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(" ");
                width = elements.length;
                height = 1;

                for (int col = 0; col < elements.length; col++) {
                    boolean isTarget = elements[col].equals("W");
                    Cell cell = new Cell(isTarget, 0, col);
                    switch (elements[col]) {
                        case "P" -> cell.setPiece(new Piece(PieceColor.PURPLE, true));
                        case "R" -> cell.setPiece(new Piece(PieceColor.RED, true));
                        case "B" -> cell.setPiece(new Piece(PieceColor.BLACK, false));
                    }
                    cells.add(cell);
                }
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(" ");
                int currentRow = height;

                for (int col = 0; col < elements.length; col++) {
                    boolean isTarget = elements[col].equals("W");
                    Cell cell = new Cell(isTarget, currentRow, col);
                    switch (elements[col]) {
                        case "P" -> cell.setPiece(new Piece(PieceColor.PURPLE, true));
                        case "R" -> cell.setPiece(new Piece(PieceColor.RED, true));
                        case "B" -> cell.setPiece(new Piece(PieceColor.BLACK, false));
                    }
                    cells.add(cell);
                }
                height++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int row, int col) {
        if (isValidPosition(row, col)) {
            int index = row * width + col;
            return cells.get(index);
        }
        return null;
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    public void printGrid() {
        for (int i = 0; i < cells.size(); i++) {
            if (i % width == 0) System.out.println();
            Cell cell = cells.get(i);
            System.out.print("[" + cell + "] ");
        }
        System.out.println();
    }

    public boolean checkAllPiecesOnTargets() {
        for (Cell cell : cells) {
            if (cell.isOccupied() && !cell.isTarget()) {
                return false;
            }
        }
        return true;
    }
}
