package Actions;

import Structure.Cell;
import Structure.GameBoard;

import java.util.*;

public class State implements Comparable<State> {
    private State parent;
    private final GameBoard value;
    private int depth = 0;
    private int cost = 0;
    private double heuristic = 0;

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

    public State(State parent, GameBoard value, int depth, int cost, double heuristic) {
        this.depth = depth;
        this.parent = parent;
        this.value = value;
        this.cost = cost;
        this.heuristic = heuristic;
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
        Collections.reverse(winPath);
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
        Object[] cost;
        ArrayList<State> nextStates = new ArrayList<>();
        for (Cell Magnetcell : this.getValue().CellsWithMagnetsPieces) {
            for (int i = 0; i < getValue().getHeight(); i++) {
                for (int j = 0; j < getValue().getWidth(); j++) {
                    Cell targetCell = getValue().getCell(i, j);
                    if (targetCell != null && !targetCell.isBlocked() && !targetCell.isOccupied()) {
                        GameBoard newBoard = this.getValue().copy();
                        PieceMover newPieceMover = new PieceMover(newBoard);
                        cost = newPieceMover.movePiece(Magnetcell.getRow(), Magnetcell.getCol(), i, j);
                        nextStates.add(new State(this, newBoard, 0, (int) cost[0], (double) cost[1]));
                    }
                }
            }
        }
        return nextStates;
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

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }
}
