package pathfinding.pathfinding_comparisons;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Test
 * @author Arne
 */
public class NodeTest {
    
    /**
     *Test
     */
    public NodeTest() {
    }
    
    /**
     *Test
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *Test
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *Test
     */
    @Before
    public void setUp() {
    }
    
    /**
     *Test
     */
    @After
    public void tearDown() {
    }

    /**
     *Test
     */
    @Test
    public void NodeCreatedCorrectly() {
        Node node = new Node(2,1);
        assertEquals(2, node.getX());
        assertEquals(1, node.getY());
        assertEquals(0, node.getPriority());
    }
    
    /**
     *Test
     */
    @Test
    public void NodeCreatedCorrectlyWithPriority() {
        Node node = new Node(3,2,8);
        assertEquals(3, node.getX());
        assertEquals(2, node.getY());
        assertEquals(8, node.getPriority());
    }
    
    /**
     *Test
     */
    @Test
    public void SetPriorityWorks() {
        Node node = new Node(2,2);
        assertEquals(0, node.getPriority());
        
        node.setPriority(10);
        assertEquals(10, node.getPriority());
    }
    
    /**
     *Test
     */
    @Test
    public void SettingPreviousNodeWorks() {
        Node node = new Node(2,2);
        Node node2 = new Node(4,3,8);
        assertEquals(null, node.getPrevious());
        node.setPrevious(node2);
        assertEquals(node2, node.getPrevious());
    }
}
