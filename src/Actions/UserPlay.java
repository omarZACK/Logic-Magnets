package Actions;
import java.util.Scanner;
import Structure.GameBoard;

public class UserPlay {
    GameBoard gameBoard;

    public UserPlay(GameBoard gameBoard){
        this.gameBoard=gameBoard;
    }
    public void play(){
        Scanner scanner = new Scanner(System.in);
        PieceMover pieceMover = new PieceMover(gameBoard);
        while (true) {
            gameBoard.printGrid();
            if (gameBoard.checkAllPiecesOnTargets()) {
                System.out.print("Congratulations! You won!");
                break;
            }
            System.out.print("Enter move (format: fromRow fromCol toRow toCol) or (exit to end the game): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")){
                System.out.println("Game Ended!\nThank you for playing!");
                break;
            }
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
