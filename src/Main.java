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
        System.out.printf("%50s%n", "Welcome to Logic Magnets!");
        System.out.println("============================================================================");
        System.out.print("Please enter the level number (1-25): ");
        int board_number;
        while (true) {
            try {
                board_number = scanner.nextInt();
                if (board_number >= 1 && board_number <= 25) {
                    break;
                } else {
                    System.out.print("Invalid input. Please enter a valid number between 1 and 25: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number for the level: ");
                scanner.next();
            }
        }
        String filename = String.format("src/Grids/board%s", board_number);
        gameBoard.loadFromFile(filename);
        gameBoard.printGrid();

        System.out.println("Choose your playing method:");
        System.out.println("1. User to play");
        System.out.println("2. Solve the game using DFS");
        System.out.println("3. Solve the game using BFS");
        System.out.println("4. Solve the game using UCS");
        System.out.println("5. Solve the game using HillClimbing");
        System.out.print("Enter your choice (1-5): ");
        int PlayMethod;
        while (true) {
            try {
                PlayMethod = scanner.nextInt();
                if (PlayMethod >= 1 && PlayMethod <= 5) {
                    break;
                } else {
                    System.out.print("Invalid input. Please enter a valid number between 1 and 5: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number for the level: ");
                scanner.next();
            }
        }
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
            case 4:
                Strategy strategyUCS = new Strategy();
                strategyUCS.UCS(gameBoard);
                break;
            case 5:
                Strategy strategyUCSH = new Strategy();
                strategyUCSH.HillClimbing(gameBoard);
                break;
            default:
                break;

        }
    }

}
