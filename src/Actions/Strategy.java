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
        System.out.println("\nDFS algorithm : ");
        double start = System.currentTimeMillis();
//        int maxDepth = 0, maxSpace=1;
        Stack<State> stack = new Stack<>();
        State node = new State(gameBoard);
        stack.push(node);
        visited.add(node);

        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.getValue().checkAllPiecesOnTargets()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
//                System.out.println("Visited : " + visited.size() + "\nMax Queue : " + maxSpace + "\nSpace Complexity (branches^maxDepth) : " + Math.pow(gameBoard.getHeight() * gameBoard.getWidth(), maxDepth) + "\nSolution Depth : " + node.getDepth() + "\nMax Depth : " + maxDepth);
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                System.out.println("Winning Path:\n");
                for (State state : node.getWinPath()) {
                    state.getValue().printGrid();
                }
                return;
            }

            children = node.generateNextStates();
            for (State childBoard : children) {
                State child = new State(node, childBoard.getValue(), node.getDepth() + 1);

                if (!visited.contains(childBoard)) {
                    stack.push(child);
//                    maxDepth = Math.max(maxDepth, node.getDepth() + 1);
//                    maxSpace = Math.max(maxSpace, stack.size());
                    visited.add(childBoard);
                }
            }
        }
        System.out.println("\n❌ No Solution Found.");
    }

    public void DFS(GameBoard gameBoard, int maxDepthLimit) {
        System.out.println("\nDFS algorithm with depth limit " + maxDepthLimit + ":");
//        int maxDepth = 0, maxSpace=1;
        double start = System.currentTimeMillis();
        Stack<State> stack = new Stack<>();
        State node = new State(gameBoard);
        stack.push(node);
        visited.add(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.getValue().checkAllPiecesOnTargets()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
//                System.out.println("Visited : " + visited.size() + "\nMax Queue : " + maxSpace + "\nSpace Complexity (branches^maxDepth) : " + Math.pow(gameBoard.getHeight() * gameBoard.getWidth(), maxDepth) + "\nSolution Depth : " + node.getDepth() + "\nMax Depth : " + maxDepth);
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                System.out.println("Winning Path:\n");
                for (State state : node.getWinPath()) {
                    state.getValue().printGrid();
                }
                return;
            }
            if (node.getDepth() < maxDepthLimit) {
                children = node.generateNextStates();
                for (State childBoard : children) {
                    State child = new State(node, childBoard.getValue(), node.getDepth() + 1);
                    if (!visited.contains(childBoard)) {
                        stack.push(child);
//                        maxDepth = Math.max(maxDepth, node.getDepth() + 1);
//                        maxSpace = Math.max(maxSpace, stack.size());
                        visited.add(childBoard);
                    }
                }
            }
        }
        System.out.println("\n❌ No Solution Found.");
    }

    public void BFS(GameBoard gameBoard) {
        System.out.println("\nBFS algorithm : ");
        double start = System.currentTimeMillis();
//        int maxDepth = 0, maxSpace=1;
        Queue<State> queue = new LinkedList<>();
        State node = new State(gameBoard);
        queue.add(node);
        visited.add(node);
        int totalCost = 0;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.getValue().checkAllPiecesOnTargets()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
//                System.out.println("Visited : " + visited.size() + "\nMax Queue : " + maxSpace + "\nSpace Complexity (branches^maxDepth) : " + Math.pow(gameBoard.getHeight() * gameBoard.getWidth(), maxDepth) + "\nSolution Depth : " + node.getDepth() + "\nMax Depth : " + maxDepth);
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                System.out.println("Winning Path:\n");
                for (State state : node.getWinPath()) {
                    state.getValue().printGrid();
                }
                System.out.println(totalCost);
                return;
            }
            children = node.generateNextStates();
            for (State childBoard : children) {
                State child = new State(node, childBoard.getValue(), node.getDepth() + 1);

                if (!visited.contains(childBoard)) {
                    queue.add(child);
//                    maxDepth = Math.max(maxDepth, node.getDepth() + 1);
//                    maxSpace = Math.max(maxSpace, queue.size());
                    visited.add(childBoard);
                }
            }
        }
        System.out.println("\n❌ No Solution Found.");
    }

    public void BFS(GameBoard gameBoard, int maxDepthLimit) {
        System.out.println("\nBFS algorithm with depth limit " + maxDepthLimit + ":");
        double start = System.currentTimeMillis();
        Queue<State> queue = new LinkedList<>();
        State node = new State(gameBoard);
        queue.add(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.getValue().checkAllPiecesOnTargets()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
//                System.out.println("Visited : " + visited.size() + "\nMax Queue : " + maxSpace + "\nSpace Complexity (branches^maxDepth) : " + Math.pow(gameBoard.getHeight() * gameBoard.getWidth(), maxDepth) + "\nSolution Depth : " + node.getDepth() + "\nMax Depth : " + maxDepth);
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                System.out.println("Winning Path:\n");
                for (State state : node.getWinPath()) {
                    state.getValue().printGrid();
                }
                return;
            }
            if (node.getDepth() < maxDepthLimit) {
                children = node.generateNextStates();
                for (State childBoard : children) {
                    State child = new State(node, childBoard.getValue(), node.getDepth() + 1);
                    if (!visited.contains(childBoard)) {
                        queue.add(child);
//                        maxDepth = Math.max(maxDepth, node.getDepth() + 1);
//                        maxSpace = Math.max(maxSpace, queue.size());
                        visited.add(childBoard);
                    }
                }
            }
        }
        System.out.println("\n❌ No Solution Found.");
    }

    public void UCS(GameBoard gameBoard) {
        System.out.println("\nUCS Algorithm:");
        double start = System.currentTimeMillis();
//        int maxDepth = 0, maxSpace=1;
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(State::getCost));
        Map<State, Integer> stateCosts = new HashMap<>();
        State initialState = new State(gameBoard);
        initialState.setCost(0);
        pq.add(initialState);
        stateCosts.put(initialState, 0);
        while (!pq.isEmpty()) {
            State currentNode = pq.poll();

            if (currentNode.getValue().checkAllPiecesOnTargets()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
//                System.out.println("Visited : " + visited.size() + "\nMax Queue : " + maxSpace + "\nSpace Complexity (branches^maxDepth) : " + Math.pow(gameBoard.getHeight() * gameBoard.getWidth(), maxDepth) + "\nSolution Depth : " + currentNode.getDepth() + "\nMax Depth : " + maxDepth);
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                List<State> winPath = currentNode.getWinPath();
                int totalCost = 0;
                System.out.println("Winning Path:\n");
                for (State state : winPath) {
                    state.getValue().printGrid();
                    totalCost = state.getCost();
                }
                System.out.println("\nTotal Cost: " + totalCost);
                return;
            }
            children = currentNode.generateNextStates();
            for(State child : children) {
                int newCost = currentNode.getCost() + child.getCost();
                if (!stateCosts.containsKey(child) || newCost < stateCosts.get(child)) {
                    child.setCost(newCost);
                    pq.add(child);
//                    maxDepth = Math.max(maxDepth, currentNode.getDepth() + 1);
//                    maxSpace = Math.max(maxSpace, pq.size());
                    stateCosts.put(child, newCost);
                }
            }
        }
        System.out.println("\n❌ No Solution Found.");
    }

    public void UCS(GameBoard gameBoard, int maxDepthLimit) {
        System.out.println("\nUCS Algorithm:");
        double start = System.currentTimeMillis();
        int maxDepth = 0, maxSpace=1;
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(State::getCost));
        Map<State, Integer> stateCosts = new HashMap<>();
        State initialState = new State(gameBoard);
        initialState.setCost(0);

        pq.add(initialState);
        stateCosts.put(initialState, 0);

        while (!pq.isEmpty()) {
            State currentNode = pq.poll();

            if (currentNode.getValue().checkAllPiecesOnTargets()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
                System.out.println("Visited : " + visited.size() + "\nMax Queue : " + maxSpace + "\nSpace Complexity (branches^maxDepth) : " + Math.pow(gameBoard.getHeight() * gameBoard.getWidth(), maxDepth) + "\nSolution Depth : " + currentNode.getDepth() + "\nMax Depth : " + maxDepth);
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                List<State> winPath = currentNode.getWinPath();
                int totalCost = 0;
                System.out.println("Winning Path:\n");
                for (State state : winPath) {
                    state.getValue().printGrid();
                    totalCost = state.getCost();
                }
                System.out.println("\nTotal Cost: " + totalCost);
                return;
            }
            if (currentNode.getDepth() < maxDepthLimit) {
                children = currentNode.generateNextStates();
                for (State child : children) {
                    int newCost = currentNode.getCost() + child.getCost();
                    if (!stateCosts.containsKey(child) || newCost < stateCosts.get(child)) {
                        child.setCost(newCost);
                        pq.add(child);
                        maxDepth = Math.max(maxDepth, currentNode.getDepth() + 1);
                        maxSpace = Math.max(maxSpace, pq.size());
                        stateCosts.put(child, newCost);
                    }
                }
            }
        }

        System.out.println("\n❌ No Solution Found.");
    }

    public void HillClimbing(GameBoard gameBoard) {
        System.out.println("\nHill climbing Search algorithm : \n");
        double start = System.currentTimeMillis();

        State node;

        node = new State(gameBoard);
        visited.add(node);

        while (true) {
            if (node.getValue().checkAllPiecesOnTargets()) {
                double end = System.currentTimeMillis();
                System.out.println("\n🎉 Goal State Reached! 🎉");
                System.out.printf("Execution Time: %.2f seconds\n", (end - start) / 1000);
                List<State> winPath = node.getWinPath();
                System.out.println("Winning Path:\n");
                for (State state : winPath) {
                    state.getValue().printGrid();
                    System.out.printf("\nHeuristic: %.2f \n" , state.getHeuristic());
                }
                return;
            }
            State bestChild = null;
            double maxH = Double.MAX_VALUE;
            children = node.generateNextStates();
            for (State childBoard : children) {
                State child = new State(node, childBoard.getValue(), node.getDepth() + 1,0,childBoard.getHeuristic());
                if (!visited.contains(childBoard)) {
                    if (child.getHeuristic() < maxH) {
                        maxH = child.getHeuristic();
                        bestChild = child;
                    }
                }
            }
            if (bestChild != null) {
                visited.add(bestChild);
                node = bestChild;
            }
            else{
                System.out.println("Stack at local maximum");
                break;
            }
        }
    }
}