package data_structures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinding.pathfinding_comparisons.Node;

public class MazeNodeListTest {
    
    public MazeNodeListTest() {
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
    public void AddingToListIncreasesSize() {
        MazeNodeList list = new MazeNodeList();
        list.add(new Node(11,10,0));
        assertEquals(1, list.size());
    }
    
    @Test
    public void GetReturnsCorrectElement() {
        MazeNodeList list = new MazeNodeList();
        Node node = new Node(11,12,4);
        Node node2 = new Node(14,16,8);
        list.add(node);
        list.add(node2);
        assertEquals(node, list.get(0));
        assertEquals(node2, list.get(1));
    }

    @Test
    public void ReverseCorrectlyReversesTheList() {
        MazeNodeList list = new MazeNodeList();
        MazeNodeList controlList = new MazeNodeList();
        
        Node node = new Node(1,2,0);
        Node node2 = new Node(3,4,8);
        
        list.add(node);
        list.add(node2);
        
        controlList.add(node);
        controlList.add(node2);
        
        list.reverse();
        
        for (int i = 0; i < 2; i++) {
            assertEquals(controlList.get(1 - i), list.get(i));
        }
    }
    
}
