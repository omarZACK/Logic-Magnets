import Actions.Strategy;
import Actions.UserPlay;
import Structure.GameBoard;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameBoard gameBoard = new GameBoard();
        System.out.println("============================================================================");
        System.out.println("\t\t\t\t\t\tWelcome to Logic Magnets");
        System.out.println("============================================================================");
        System.out.print("Please enter the level number (1-25): ");
        int board_number = 0;
        while (true) {
            try {
                board_number = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number for the level: ");
                scanner.next();
            }
        }
        String filename = String.format("src/Grids/board%s", board_number);
        gameBoard.loadFromFile(filename);

        System.out.println("Choose your playing method:");
        System.out.println("1. User to play");
        System.out.println("2. Solve the game using DFS");
        System.out.println("3. Solve the game using BFS");
        System.out.print("Enter your choice (1-3): ");
        int PlayMethod = scanner.nextInt();
        switch (PlayMethod){
            case 1:
                UserPlay userPlay = new UserPlay(gameBoard);
                userPlay.play();
                break;
            case 2:
                Strategy strategy = new Strategy();
                strategy.DFS(gameBoard);
                break;
            case 3:
                Strategy strategyBFS = new Strategy();
                strategyBFS.BFS(gameBoard);
                break;
            default:
                break;

        }
    }

}
