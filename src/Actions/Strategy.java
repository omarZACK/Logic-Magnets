package Actions;
import Structure.GameBoard;
import java.util.*;

public class Strategy {
    private final Set<State> visited;
    List<State> children;

    public Strategy() {
        this.visited = new HashSet<>();
    }

    public void DFS(GameBoard gameBoard) {
        System.out.println("\nDFS algorithm : \n");
        double start = System.currentTimeMillis();

        Stack<State> stack = new Stack<>();
        State node = new State(gameBoard);
        stack.push(node);
        visited.add(node);

        while (!stack.isEmpty()) {
            node = stack.pop();
            node.getValue().printGrid();
            if (node.isGoalState()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                System.out.println("Winning Path:\n");
                for (State state : node.getWinPath().reversed()){
                    state.getValue().printGrid();
                }
                return;
            }
            children = node.generateNextStates();
            for (State childBoard : children) {
                State child = new State(node, childBoard.getValue(),node.getDepth()+1);

                if (!visited.contains(childBoard)){
                    stack.push(child);
                    visited.add(childBoard);
                }
            }
        }
        System.out.println("No solution found.");
    }

    public void DFS(GameBoard gameBoard, int maxDepthLimit) {
        System.out.println("\nDFS algorithm with depth limit " + maxDepthLimit + ":\n");
        double start = System.currentTimeMillis();
        Stack<State> stack = new Stack<>();
        State node = new State(gameBoard);
        stack.push(node);
        visited.add(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            node.getValue().printGrid();
            if (node.isGoalState()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                System.out.println("Winning Path:\n");
                for (State state : node.getWinPath().reversed()){
                    state.getValue().printGrid();
                }
                return;
            }
            if (node.getDepth() < maxDepthLimit) {
                children = node.generateNextStates();
                for (State childBoard : children) {
                    State child = new State(node, childBoard.getValue(),node.getDepth()+1);
                    if (!visited.contains(childBoard)) {
                        stack.push(child);
                        visited.add(childBoard);
                    }
                }
            }
        }
        System.out.println("No solution found.");
    }
    public void BFS(GameBoard gameBoard) {
        System.out.println("\nDFS algorithm : \n");
        double start = System.currentTimeMillis();

        Queue<State> queue = new LinkedList<>();
        State node = new State(gameBoard);
        queue.add(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            node = queue.poll();
            node.getValue().printGrid();
            if (node.isGoalState()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                System.out.println("Winning Path:\n");
                for (State state : node.getWinPath().reversed()){
                    state.getValue().printGrid();
                }
                return;
            }
            children = node.generateNextStates();
            for (State childBoard : children) {
                State child = new State(node, childBoard.getValue(),node.getDepth()+1);

                if (!visited.contains(childBoard)){
                    queue.add(child);
                    visited.add(childBoard);
                }
            }
        }
        System.out.println("No solution found.");
    }

    public void BFS(GameBoard gameBoard, int maxDepthLimit) {
        System.out.println("\nDFS algorithm with depth limit " + maxDepthLimit + ":\n");
        double start = System.currentTimeMillis();
        Queue<State> queue = new LinkedList<>();
        State node = new State(gameBoard);
        queue.add(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.getValue().printGrid();
            if (node.isGoalState()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                System.out.println("Winning Path:\n");
                for (State state : node.getWinPath().reversed()){
                    state.getValue().printGrid();
                }
                return;
            }
            if (node.getDepth() < maxDepthLimit) {
                children = node.generateNextStates();
                for (State childBoard : children) {
                    State child = new State(node, childBoard.getValue(),node.getDepth()+1);
                    if (!visited.contains(childBoard)) {
                        queue.add(child);
                        visited.add(childBoard);
                    }
                }
            }
        }
        System.out.println("No solution found.");
    }
}
