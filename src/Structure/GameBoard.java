package Structure;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GameBoard {
    private List<Cell> cells;
    public List<Cell> TargetCells;
    public List<Cell> cellsWithPieces;
    public List<Cell> CellsWithMagnetsPieces;
    private int width;
    private int height;

    public GameBoard() {
        cells = new ArrayList<>();
        TargetCells = new ArrayList<>();
        CellsWithMagnetsPieces = new ArrayList<>();
        cellsWithPieces = new ArrayList<>();
    }

    public void loadFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int row = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(" ");
                if (row == 0) {
                    width = elements.length;
                }
                for (int col = 0; col < elements.length; col++) {
                    Cell cell = createCell(row, col, elements[col]);
                    cells.add(cell);
                    if (cell.isTarget()) {
                        TargetCells.add(cell);
                    }
                    if (cell.getPiece() != null) {
                        cellsWithPieces.add(cell);
                    }
                    if (cell.getPiece() != null && cell.getPiece().isMagnetic()) {
                        CellsWithMagnetsPieces.add(cell);
                    }
                }
                row++;
            }
            height = row;
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
                case "S":
                    cell.setBlocked(true);
                    break;
                case "P":
                    cell.setPiece(new Piece(PieceColor.PURPLE, true));
                    break;
                case "R":
                    cell.setPiece(new Piece(PieceColor.RED, true));
                    break;
                case "B":
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

    public boolean isValidPosition(int row, int col) {
        return row < 0 || row >= height || col < 0 || col >= width || getCell(row, col).isBlocked();
    }

    public void printGrid() {
        System.out.print("   ");
        for (int col = 0; col < width; col++) {
            System.out.printf("%5d ", col);
        }
        System.out.println();

        for (int row = 0; row < height; row++) {
            System.out.printf("%2d ", row);

            for (int col = 0; col < width; col++) {
                Cell cell = getCell(row, col);
                System.out.printf("[%3s] ", cell);
            }
            System.out.println();
        }
        System.out.println();
    }


    public boolean checkAllPiecesOnTargets() {
        for (Cell cell : this.cellsWithPieces) {
            if (!cell.isTarget()) {
                return false;
            }
        }
        return true;
    }

    public GameBoard copy() {
        GameBoard copy = new GameBoard();
        copy.width = this.width;
        copy.height = this.height;
        copy.cells = new ArrayList<>();
        for (Cell cell : this.cells) {
            copy.cells.add(cell.copy());
        }
        copy.TargetCells = new ArrayList<>();
        for (Cell targetCell : this.TargetCells) {
            copy.TargetCells.add(targetCell.copy());
        }
        copy.cellsWithPieces = new ArrayList<>();
        for (Cell pieceCell : this.cellsWithPieces) {
            copy.cellsWithPieces.add(pieceCell.copy());
        }
        copy.CellsWithMagnetsPieces = new ArrayList<>();
        for (Cell magnetCell : this.CellsWithMagnetsPieces) {
            copy.CellsWithMagnetsPieces.add(magnetCell.copy());
        }

        return copy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GameBoard other = (GameBoard) obj;
        if (this.width != other.width || this.height != other.height) {
            return false;
        }
        return this.cells.equals(other.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells, width, height);
    }

}
