import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameBoard gameBoard = new GameBoard();
        System.out.print("Enter the level number: ");
        String board_number = scanner.nextLine();
        String filename = String.format("src/Grids/board%s", board_number);
        gameBoard.loadFromFile(filename);
//        Strategies strategies = new Strategies(gameBoard);
//        strategies.DFS();
        PieceMover pieceMover = new PieceMover(gameBoard);
        while (true) {
            gameBoard.printGrid();
            // Check for win condition
            if (gameBoard.checkAllPiecesOnTargets()) {
                System.out.println("Congratulations! All pieces are on white cells.");
                break;
            }
            System.out.print("Enter move (format: fromRow fromCol toRow toCol): ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if (parts.length == 4) {
                int fromRow = Integer.parseInt(parts[0]);
                int fromCol = Integer.parseInt(parts[1]);
                int toRow = Integer.parseInt(parts[2]);
                int toCol = Integer.parseInt(parts[3]);
                pieceMover.movePiece(fromRow, fromCol, toRow, toCol);
            } else {
                System.out.println("Invalid input. Please enter four integers.");
            }
        }
        scanner.close();
    }
}
