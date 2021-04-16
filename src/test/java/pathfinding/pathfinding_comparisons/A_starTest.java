package pathfinding.pathfinding_comparisons;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class A_starTest {
    
    public A_starTest() {
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
    public void InitializedCorrectly() {
        int W = Integer.MAX_VALUE;
        int[][] maze =
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
        
        A_star a_star = new A_star(maze);
        assertArrayEquals(a_star.maze, maze);
        assertEquals(maze.length,a_star.distance.length);
        assertEquals(maze.length,a_star.visited.length);
        assertEquals(0,a_star.GetPath().size());
        assertEquals(0,a_star.queue.size());
    }
    
    @Test
    public void PathFoundIsValid() {
        int W = Integer.MAX_VALUE;
        int[][] maze =
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
        
        A_star a_star = new A_star(maze);
        
        Node start = new Node(0,1);
        Node destination = new Node(7,5);
        
        a_star.FindPath(start, destination);
        ArrayList<Node> path = a_star.GetPath();
        path.forEach((node) -> {
            assertEquals(true, a_star.IsValid(node));
        });
    }
    
    @Test
    public void SearchingNewPathWorks() {
        int W = Integer.MAX_VALUE;
        int[][] maze =
        {
        {1,1,1,W,1},
        {W,W,1,W,1},
        {W,W,1,1,1},
        {1,W,1,W,1},
        {1,1,1,W,1}
        };
        
        A_star a_star = new A_star(maze);
        
        Node start = new Node(2,2);
        Node destination = new Node(0,3);
        
        a_star.FindPath(start, destination);
        ArrayList<Node> path = a_star.GetPath();
        
        ArrayList<Node> correctPath = new ArrayList<>();
        correctPath.add(new Node(2,2));
        correctPath.add(new Node(2,3));
        correctPath.add(new Node(2,4));
        correctPath.add(new Node(1,4));
        correctPath.add(new Node(0,4));
        correctPath.add(new Node(0,3));
        
        assertEquals(correctPath.size(), path.size());
        
        for (int i = 0; i < correctPath.size(); i++) {
            assertEquals(correctPath.get(i).getX(), path.get(i).getX());
            assertEquals(correctPath.get(i).getY(), path.get(i).getY());
        }
        
        start = new Node(0,3);
        destination = new Node(4,0);
        
        a_star.FindPath(start, destination);
        path = a_star.GetPath();
        
        correctPath = new ArrayList<>();
        correctPath.add(new Node(0,3));
        correctPath.add(new Node(0,4));
        correctPath.add(new Node(1,4));
        correctPath.add(new Node(2,4));
        correctPath.add(new Node(2,3));
        correctPath.add(new Node(2,2));
        correctPath.add(new Node(3,2));
        correctPath.add(new Node(4,2));
        correctPath.add(new Node(4,1));
        correctPath.add(new Node(4,0));
        
        assertEquals(correctPath.size(), path.size());
        
        for (int i = 0; i < correctPath.size(); i++) {
            assertEquals(correctPath.get(i).getX(), path.get(i).getX());
            assertEquals(correctPath.get(i).getY(), path.get(i).getY());
        }
    }
    
    @Test
    public void PathFoundIsShortest() {
        int[][] maze =
        {
        {1,10,1},
        {1,10,1},
        {1,1,1}
        };
        
        A_star a_star = new A_star(maze);
        Node start = new Node(0,0);
        Node destination = new Node(2,0);
        a_star.FindPath(start, destination);
        
        assertEquals(6, a_star.LengthOfPath());
    }
}