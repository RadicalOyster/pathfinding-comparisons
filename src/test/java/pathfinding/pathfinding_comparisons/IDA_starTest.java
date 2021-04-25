package pathfinding.pathfinding_comparisons;

import data_structures.MazeNodeList;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class IDA_starTest {
    
    public IDA_starTest() {
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
        
        IDA_star ida = new IDA_star(maze);
        assertArrayEquals(ida.maze, maze);
        assertEquals(maze.length,ida.distance.length);
        assertEquals(0,ida.GetPath().size());
        assertEquals(0,ida.getPath().size());
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
        
        IDA_star ida = new IDA_star(maze);
        
        Node start = new Node(0,1);
        Node destination = new Node(7,5);
        
        ida.FindPath(start, destination);
        MazeNodeList path = ida.GetPath();
        
        for (int i = 0; i < path.size(); i++) {
            assertEquals(true, ida.IsValid(path.get(i)));
        }
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
        
        IDA_star ida = new IDA_star(maze);
        
        Node start = new Node(2,2);
        Node destination = new Node(0,3);
        
        ida.FindPath(start, destination);
        MazeNodeList path = ida.GetPath();
        
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
        
        ida.FindPath(start, destination);
        path = ida.GetPath();
        
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
        
        IDA_star ida = new IDA_star(maze);
        Node start = new Node(0,0);
        Node destination = new Node(2,0);
        ida.FindPath(start, destination);
        
        assertEquals(6, ida.LengthOfPath());
    }
}