package pathfinding.pathfinding_comparisons;

import data_structures.NodeHeap;
import data_structures.MazeNodeList;

/**
 * <h1>IDA* Algorithm</h1>
 * An implementation of the IDA* algorithm. Utilizes iterative deepening with a
 * to explore nodes until a path to the target node is found. A heuristic
 * function is used to determine the best candidate to explore next.
 */

public class IDA_star {

    final int[][] maze;
    int[][] distance;
    private boolean pathFound;
    private NodeHeap path;
    private MazeNodeList finalPath;

    /**
     *
     * @param maze The maze to solve. The cost of moving to a node is represented
     * by an int value with walls being marked as Integer.MAX_VALUE
     */
    public IDA_star(int[][] maze) {
        this.maze = maze;
        this.Initialize();
    }
    
    //Initialize data structures
    private void Initialize() {
        this.distance = new int[maze.length][maze[0].length];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        this.pathFound = false;
        this.path = new NodeHeap();
        this.finalPath = new MazeNodeList();
    }
    
    //Calculates the manhattan distance between two nodes.
    private int ManhattanDistance(Node node1, Node node2) {
        return Math.abs(node1.getX() - node2.getX()) + Math.abs(node1.getY() - node2.getY());
    }
    
    //Checks if a given node is within the bounds of the maze

    /**
     *
     * @param node
     * @return True if node is valid, false if not.
     */
    public boolean IsValid(Node node) {
        return (node.getY() >= 0 && node.getY() < this.maze.length) && (node.getX() >= 0 && node.getX() < this.maze[0].length);
    }
    
    /**
    * Returns child nodes of given node.
    * @param node The parent node.
    * @return PriorityQueue containing all the child nodes of the given node.
    */
    private NodeHeap Successors(Node node) {
        NodeHeap successors = new NodeHeap();
        //System.out.println("CURRENTLY EXPANDING " + node);
        for (int i = 0; i < 4; i++) {
            Node newNode = new Node(node.getX(), node.getY(), 0);

            switch (i) {
                case 0:
                    newNode.setX(newNode.getX() + 1);
                    break;
                case 1:
                    newNode.setX(newNode.getX() - 1);
                    break;
                case 2:
                    newNode.setY(newNode.getY() + 1);
                    break;
                default:
                    newNode.setY(newNode.getY() - 1);
                    break;
            }

            if (IsValid(newNode) && this.maze[newNode.getY()][newNode.getX()] != Integer.MAX_VALUE) {
                newNode.setPriority(node.priority + this.maze[newNode.getY()][newNode.getX()]);
                newNode.setPrevious(node);
                //System.out.println("NEW NODE " + newNode + " priority: " + newNode.priority);
                successors.add(newNode);
            }
        }
        return successors;
    }
    
    /**
     * Runs the algorithm to find the shortest path between two points.
     * @param start The start node.
     * @param destination The destination node.
     */
    public void FindPath(Node start, Node destination) {
        this.Initialize();
        this.distance[start.getY()][start.getX()] = 0;
        int threshold = ManhattanDistance(start, destination);
        this.path.insert(start);
        
        while (true) {
            threshold = this.Search(0, threshold, destination);
            if (threshold == Integer.MAX_VALUE) {
                //System.out.println("No path found");
                break;
            } else if (this.pathFound) {
                Node current = this.path.peekLast();
                while (true) {
                    this.finalPath.add(current);
                    if (current.equals(start)) {
                        break;
                    }
                    current = current.previous;
                }
                break;
            }
        }
        this.finalPath.reverse();
    }

    /**
     *
     * @param distance Distance of current path.
     * @param threshold Threshold for current iteration.
     * @param destination The destination node.
     * @return Threshold for the next iteration.
     */
    private int Search(int distance, int threshold, Node destination) {
        Node currentNode = this.path.peekLast();

        int current_estimate = distance + ManhattanDistance(currentNode, destination);

        if (current_estimate > threshold) {
            return current_estimate;
        }
        
        if (currentNode.equals(destination)) {
            this.pathFound = true;
            return -1;
        }

        int min = Integer.MAX_VALUE;
        NodeHeap successors = this.Successors(currentNode);
        while (successors.size() > 0) {
            Node successor = successors.poll();
            if (!(this.path.contains(successor))) {
                this.path.add(successor);
                int t = Search(distance + this.maze[successor.getY()][successor.getX()], threshold, destination);
                if (this.pathFound) {
                    return -1;
                }
                if (t < min) {
                    min = t;
                }
                path.pollLast();
            }
        }

        return min;
    }

    /**
     * Converts the maze into a simplified form to help visualize the shortest
     * path.
     *
     * @return A 2-dimensional array of type char. 'X' represents a wall, 'S'
     * represents the starting node, 'G' the destination node and '*' a node on
     * the path.
     */
    public char[][] GetVisualization() {
        char[][] visualization = new char[this.maze.length][this.maze[0].length];
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[0].length; j++) {
                char character = '-';
                if (this.maze[i][j] == Integer.MAX_VALUE) {
                    character = 'X';
                }
                visualization[i][j] = character;
            }
        }

        for (int i = 0; i < this.finalPath.size(); i++) {
            Node current = this.finalPath.get(i);
            char character;
            if (i == 0) {
                character = 'S';
            } else if (i == this.finalPath.size() - 1) {
                character = 'G';
            } else {
                character = '*';
            }
            visualization[current.getY()][current.getX()] = character;
        }
        return visualization;
    }
    
    /**
     * Returns the last calculated path.
     *
     * @return The current path.
     */
    public MazeNodeList GetPath() {
        return this.finalPath;
    }

    /**
     *
     */
    public void PrintVisualization() {
        char[][] visualization = this.GetVisualization();
        for (char[] row : visualization) {
            for (int i = 0; i < visualization[0].length; i++) {
                System.out.print(row[i] + " ");
            }
            System.out.println();
        }
    }
    
        /**
     * Gets the length of the path, required for unit testing
     *
     * @return Length of the current path
     */
    public int LengthOfPath() {
        if (this.finalPath.size() <= 0) {
            return -1;
        }

        int length = 0;
        for (int i = 1; i < this.finalPath.size(); i++) {
            length += this.maze[this.finalPath.get(i).getY()][this.finalPath.get(i).getX()];
        }

        return length;
    }
    
    public NodeHeap getPath() {
        return this.path;
    }
}
