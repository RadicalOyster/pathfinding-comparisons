package pathfinding.pathfinding_comparisons;

import domain.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {
    
    public NodeTest() {
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
    public void NodeCreatedCorrectly() {
        Node node = new Node(2,1);
        assertEquals(2, node.getX());
        assertEquals(1, node.getY());
        assertEquals(0, node.getPriority());
    }
    
    @Test
    public void NodeCreatedCorrectlyWithPriority() {
        Node node = new Node(3,2,8);
        assertEquals(3, node.getX());
        assertEquals(2, node.getY());
        assertEquals(8, node.getPriority());
    }
    
    @Test
    public void SetPriorityWorks() {
        Node node = new Node(2,2);
        assertEquals(0, node.getPriority());
        
        node.setPriority(10);
        assertEquals(10, node.getPriority());
    }

    @Test
    public void SettingPreviousNodeWorks() {
        Node node = new Node(2,2);
        Node node2 = new Node(4,3,8);
        assertEquals(null, node.getPrevious());
        node.setPrevious(node2);
        assertEquals(node2, node.getPrevious());
    }
    
    @Test
    public void EqualsWorks() {
        Node node = new Node(2,2,0);
        Node node2 = new Node(2,2,1);
        Node node3 = new Node(2,3,0);
        
        assertEquals(true, node.equals(node2));
        assertEquals(false, node.equals(node3));
    }
    
    @Test
    public void CompareToWorks() {
        Node node = new Node(2,2,0);
        Node node2 = new Node(2,2,1);
        Node node3 = new Node(2,3,4);
        
        assertEquals(-1, node.compareTo(node2));
        assertEquals(3, node3.compareTo(node2));
    }
}
