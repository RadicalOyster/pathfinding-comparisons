package pathfinding.pathfinding_comparisons;

import data_structures.MazeNodeList;
import data_structures.NodeHeap;

/**
 * <h1>Pathfinding Comparisons</h1>
 * Main class, currently doesn't do much.
 */
public class PathfindingComparisons {

    public static void main(String[] args) {
        //Create maze
        int W = Integer.MAX_VALUE;
        int[][] testMaze
                = {
                    {W, W, 1, W, 1, 1, 1, 1},
                    {1, 1, W, 1, 1, W, W, 1},
                    {1, 1, 1, W, W, W, 1, 1},
                    {W, W, 1, W, 1, 1, 1, 1},
                    {1, W, 1, W, 1, 1, W, 1},
                    {1, W, 1, 1, W, 1, 1, W},
                    {1, 1, 1, W, W, W, 1, 1},
                    {1, W, 1, 1, W, 1, 1, 1},
                    {1, W, 1, 1, 1, 1, 1, 1},};

        //Create test nodes and run the algorithms
        Node start = new Node(3, 1);
        Node destination = new Node(1, 2);
        
        //Dijkstra
        System.out.println("Running dijkstra: ");
        Dijkstra dijkstra = new Dijkstra(testMaze);
        dijkstra.FindPath(start, destination);
        dijkstra.PrintVisualization();
        System.out.println("\n_______\n");
        
        //A*
        System.out.println("Running A*: ");
        A_star a_star = new A_star(testMaze);
        a_star.FindPath(start, destination);
        a_star.PrintVisualization();
        System.out.println("\n_______\n");

        //IDA*
        System.out.println("Running IDA*: ");
        IDA_star ida = new IDA_star(testMaze);
        ida.FindPath(start, destination);
        ida.PrintVisualization();
        
                int[][] maze =
        {
        {1,10,1},
        {1,10,1},
        {1,1,1}
        };
        
        A_star d_star = new A_star(maze);
        Node start2 = new Node(0,0);
        Node destination2 = new Node(2,0);
        d_star.FindPath(start2, destination2);
        
        d_star.PrintVisualization();
    }

}
