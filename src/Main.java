import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.loadFromFile("src/Grids/board3.txt");
//        Strategies strategies = new Strategies(gameBoard);
//        strategies.DFS();
        PieceMover pieceMover = new PieceMover(gameBoard);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            gameBoard.printGrid();
            // Check for win condition
            if (gameBoard.checkAllPiecesOnTargets()) {
                System.out.println("Congratulations! All pieces are on target cells.");
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

                // Convert to 1D index
                pieceMover.movePiece(fromRow, fromCol,toRow, toCol);
            } else {
                System.out.println("Invalid input. Please enter four integers.");
            }
        }
        scanner.close();
    }
}
