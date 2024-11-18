package Actions;

import Structure.Cell;
import Structure.GameBoard;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class State implements Comparable<State> {
    private State parent;
    private final GameBoard value;
    private int depth = 0;
    private int cost = 0;

    public State(GameBoard value) {
        this.value = value;
    }

    public State(State parent, GameBoard value) {
        this.parent = parent;
        this.value = value;
    }

    public State(State parent, GameBoard value, int depth) {
        this.parent = parent;
        this.value = value;
        this.depth = depth;
    }

    public State(State parent, GameBoard value, int depth, int cost) {
        this.depth = depth;
        this.parent = parent;
        this.value = value;
        this.cost = cost;
    }

    public boolean hasPrevious() {
        return this.parent != null;
    }

    public State getParent() {
        return parent;
    }

    public List<State> getWinPath() {
        List<State> winPath = new ArrayList<>();
        State current = this;
        while (current.hasPrevious()) {
            winPath.add(current);
            current = current.getParent();
        }
        return winPath;
    }

    public GameBoard getValue() {
        return value;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ArrayList<State> generateNextStates() {
        int cost;
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
                                cost = newPieceMover.movePiece(row, col, i, j);
                                nextStates.add(new State(this, newBoard, 0,1));
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return this.value.equals(state.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(State other) {
        return Integer.compare(this.cost, other.cost);
    }
}
