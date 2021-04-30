package algorithms;

import data_structures.MazeNodeList;
import data_structures.NodeHeap;
import domain.Node;
import domain.Timer;

/**
 * <h1>Dijkstra's Algorithm</h1>
 * An implementation of Dijkstra's algorithm to find the shortest path in a maze
 * represented by a 2-dimensional array. Each point in the array represents the
 * cost of moving to that node with walls being marked as Integer.MAX_VALUE.
 */
public class Dijkstra {

    NodeHeap queue;
    int[][] maze;
    boolean[][] visited;
    int[][] distance;
    boolean pathFound;
    MazeNodeList path;
    Timer timer;

    /**
     *
     * @param maze The maze to solve. The cost of moving to a node is represented
     * by an int value with walls being marked as Integer.MAX_VALUE
     */
    public Dijkstra(int[][] maze) {
        this.maze = maze;
        this.Initialize();
        this.timer = new Timer();
    }

    private void Initialize() {
        this.visited = new boolean[maze.length][maze[0].length];
        this.distance = new int[maze.length][maze[0].length];
        this.path = new MazeNodeList();

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                this.distance[i][j] = Integer.MAX_VALUE;
            }
        }

        this.pathFound = false;
        this.queue = new NodeHeap();
    }

    /**
     * Checks if node is in bounds
     *
     * @param node Node to check
     * @return Returns true if node is in bounds, false if not.
     */
    public boolean IsValid(Node node) {
        return (node.getY() >= 0 && node.getY() < this.maze.length) && (node.getX() >= 0 && node.getX() < this.maze[0].length);
    }

    private void Traverse(Node node, Node destination) {
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

            if (!(IsValid(newNode)) || this.visited[newNode.getY()][newNode.getX()]) {
                continue;
            }

            if (this.maze[newNode.getY()][newNode.getX()] == Integer.MAX_VALUE) {
                continue;
            }

            int distance_to_node = this.distance[node.getY()][node.getX()] + this.maze[newNode.getY()][newNode.getX()];
            newNode.setPrevious(node);

            newNode.setPriority(distance_to_node);
            this.queue.insert(newNode);
            this.distance[newNode.getY()][newNode.getX()] = distance_to_node;
            this.visited[newNode.getY()][newNode.getX()] = true;

            if (newNode.getX() == destination.getX() && newNode.getY() == destination.getY()) {
                this.pathFound = true;
                destination.setPrevious(node);
                break;
            }
        }
    }

    /**
     * Run the algorithm to calculate the shortest path between the start point
     * destination.
     *
     * @param start The start node.
     * @param destination The destination node.
     * @return 
     */
    public boolean FindPath(Node start, Node destination) {
        this.timer.Start();
        if (!IsValid(start) || !IsValid(destination)) {
            this.timer.Stop();
            return false;
        }
        //Reset everything to the initial state
        Initialize();
        this.distance[start.getX()][start.getY()] = 0;
        this.visited[start.getX()][start.getY()] = true;
        this.queue.insert(start);
        
        //Go through nodes until all nodes have been explored or a path is found
        while (!(this.pathFound) && queue.size() > 0) {
            Node head = queue.poll();
            Traverse(head, destination);
        }
        
        //If a path is found, construct the path, else notify the user that
        //no path has been found
        if (this.pathFound) {
            Node currentNode = destination;
            this.path = new MazeNodeList();
            while (true) {
                this.path.add(currentNode);
                if (currentNode.getX() == start.getX() && currentNode.getY() == start.getY()) {
                    break;
                }
                currentNode = currentNode.getPrevious();
            }
            this.timer.Stop();
            this.path.reverse();
            return true;
        } else {
            this.timer.Stop();
            return false;
        }
    }

    /**
     * Returns the last calculated path.
     *
     * @return The current path.
     */
    public MazeNodeList GetPath() {
        return this.path;
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

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                char character = '-';
                if (this.maze[i][j] == Integer.MAX_VALUE) {
                    character = 'X';
                }
                visualization[i][j] = character;
            }
        }

        if (this.path.size() > 0) {
            for (int i = 0; i < this.path.size(); i++) {
                Node node = path.get(i);
                visualization[node.getY()][node.getX()] = '*';
            }

            visualization[this.path.get(0).getY()][this.path.get(0).getX()] = 'S';
            visualization[this.path.get(this.path.size() - 1).getY()][this.path.get(this.path.size() - 1).getX()] = 'G';
        }

        return visualization;
    }

    /**
     * Prints a visualization of the shortest path.
     */
    public void PrintVisualization() {
        char[][] visualization = GetVisualization();

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
        if (path.size() <= 0) {
            return -1;
        }

        int length = 0;
        for (int i = 1; i < path.size(); i++) {
            length += this.maze[path.get(i).getY()][path.get(i).getX()];
        }

        return length;
    }
    
    public long getDuration() {
        return this.timer.getDuration();
    }
}