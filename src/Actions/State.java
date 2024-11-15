package Actions;
import Structure.Cell;
import Structure.GameBoard;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class State {
    private  State parent;
    private final GameBoard value;
    private int depth=0;
//    private int cost = 1;

    public State(GameBoard value) {
        this.value= value;
    }


    public State(State parent, GameBoard value)
    {
        this.parent = parent;
        this.value = value;
    }
    public State(State parent, GameBoard value,int depth)
    {
        this.parent = parent;
        this.value = value;
        this.depth = depth;
    }
//    public State(State parent, GameBoard value, int cost)
//    {
//        this.parent = parent;
//        this.value = value;
//        this.cost = cost;
//    }


    public boolean hasPrevious()
    {
        return this.parent != null;
    }

    public State getParent() {
        return parent;
    }

    public List<State> getWinPath() {
        List<State> winPath = new ArrayList<>();
        State current = this;
        while (current != null) {
            winPath.add(current);
            current = current.getParent();
        }
        return winPath;
    }
    public GameBoard getValue() {
        return value;
    }

//    public int getCost() {
//        return cost;
//    }
//
//    public void setCost(int cost) {
//        this.cost = cost;
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        // Compare relevant fields (e.g., game board configuration) for equality
        return this.value.equals(state.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value); // Use game board's hash as the hash code
    }

    public ArrayList<State> generateNextStates() {

        ArrayList<State> nextStates = new ArrayList<>();
        for (int row = 0; row < getValue().getHeight(); row++) {
            for (int col = 0; col < getValue().getWidth(); col++) {
                Cell cell = getValue().getCell(row, col);
                if (cell.getPiece() != null && cell.getPiece().isMagnetic()) {
                    for (int i = 0; i < getValue().getHeight(); i++) {
                        for (int j = 0; j < getValue().getWidth(); j++) {
                            Cell targetCell = getValue().getCell(i, j);
                            if (targetCell != null && !targetCell.isBlocked() && !targetCell.isOccupied()) {
                                GameBoard newBoard = this.getValue().copy();
                                PieceMover newPieceMover = new PieceMover(newBoard);
                                newPieceMover.movePiece(row, col, i, j);
                                nextStates.add(new State(this, newBoard));
                            }
                        }
                    }
                }
            }
        }

        return nextStates;
    }

    public boolean isGoalState() {
        for (int row = 0; row < getValue().getHeight(); row++) {
            for (int col = 0; col < getValue().getWidth(); col++) {
                Cell cell = getValue().getCell(row, col);
                if (cell.isTarget() && !cell.isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getDepth() {
        return depth;
    }
}
