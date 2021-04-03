package test;

import main.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arne
 */
public class DijkstraTest {
    
    public DijkstraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
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
        
        Node start = new Node(3, 1);
        Node destination = new Node(1, 2);
        
        Dijkstra dijkstra = new Dijkstra(testMaze);
        dijkstra.FindPath(start, destination);
    }
}
