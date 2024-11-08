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
            // Load the board1 row by row
            int row = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(" ");

                // Adjust width if necessary (only needed for the first row)
                if (row == 0) {
                    width = elements.length;
                }

                for (int col = 0; col < elements.length; col++) {
                    // Process each element (cell) in the current row
                    Cell cell = createCell(row, col, elements[col]);
                    cells.add(cell);
                }

                row++;  // Move to the next row
            }
            height = row;  // Set the height of the board1
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    private Cell createCell(int row, int col, String element) {
        boolean isTarget = element.contains("W");
        Cell cell = new Cell(isTarget, row, col);

        String[] elements = element.split(",");
        for (String el : elements) {
            switch (el) {
                case "S":  // Blocked cell
                    cell.setBlocked(true);
                    break;
                case "P":  // Piece of type PURPLE
                    cell.setPiece(new Piece(PieceColor.PURPLE, true));
                    break;
                case "R":  // Piece of type RED
                    cell.setPiece(new Piece(PieceColor.RED, true));
                    break;
                case "B":  // Piece of type BLACK
                    cell.setPiece(new Piece(PieceColor.BLACK, false));
                    break;
            }
        }

        return cell;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public Cell getCell(int row, int col) {
        // Ensure we don't recursively call isValidPosition and loop
        if (row >= 0 && row < height && col >= 0 && col < width) {
            int index = row * width + col;
            return cells.get(index);
        }
        return null;
    }

    public boolean isValidPositions(int row, int col) {
        // Only check the boundaries and blocked cells directly
        return row < 0 || row >= height || col < 0 || col >= width || getCell(row, col).isBlocked();
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
