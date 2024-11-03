import java.util.*;
import java.util.HashSet;
public class Strategies {
    private GameBoard gameBoard;
    public static HashSet<GameBoard> visited = new HashSet<>();

    public Strategies(GameBoard board){
        this.gameBoard = board;
    }


    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }


    public void DFS(){
        GameBoard currentBoard = this.gameBoard;
        visited.add(currentBoard);
        PieceMover currentPieceMover = new PieceMover(currentBoard);
        // System.out.println(Arrays.toString(currentPieceMover.getPossibleMoves().get(i)));


    }
}
