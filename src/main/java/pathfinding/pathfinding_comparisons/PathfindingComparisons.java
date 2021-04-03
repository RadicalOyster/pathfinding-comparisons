package pathfinding.pathfinding_comparisons;

/**
 * <h1>Pathfinding Comparisons</h1>
 * Main class, currently doesn't do much.
 */
public class PathfindingComparisons {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        //Create maze
        int W = Integer.MAX_VALUE;
        int[][] testMaze =
        {
        {W,W,1,W,1,1,1,1},
        {1,1,W,1,1,W,W,1},
        {1,1,1,W,W,W,1,1},
        {W,W,1,W,1,1,1,1},
        {1,W,1,W,1,1,W,1},
        {1,W,1,1,W,1,1,W},
        {1,1,1,W,W,W,1,1},
        {1,W,1,1,W,1,1,1},
        {1,W,1,1,1,1,1,1},
        };
        
        //Create test nodes and run the algorithm
        Node start = new Node(3, 1);
        Node destination = new Node(1, 2);
        
        Dijkstra dijkstra = new Dijkstra(testMaze);
        dijkstra.FindPath(start, destination);
        
        System.out.println(dijkstra.GetPath().toString());
        dijkstra.PrintVisualization();
        
        start = new Node(0,4);
        destination = new Node(7,4);
        dijkstra.FindPath(start, destination);
        
        System.out.println("");
        
        System.out.println(dijkstra.GetPath().toString());
        dijkstra.PrintVisualization();
    }
    
}
