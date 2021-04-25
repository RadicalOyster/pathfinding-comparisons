package data_structures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pathfinding.pathfinding_comparisons.Node;

/**
 *
 * @author Arne
 */
public class NodeHeapTest {
    
    public NodeHeapTest() {
        
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

    /**
     * Test of insert method, of class NodeHeap.
     */
    @Test
    public void InsertingNodeIncreasesSize() {
        NodeHeap heap = new NodeHeap();
        Node node = new Node(12,11,0);
        
        heap.insert(node);
        assertEquals(1,heap.size());
    }
    
    @Test
    public void HeapExpandsWhenInsertingPastInitialCapacity() {
        NodeHeap heap = new NodeHeap();
        Node node = new Node(12,11,0);
        heap.insert(node);
        heap.insert(node);
        heap.insert(node);
        heap.insert(node);
        heap.insert(node);
        heap.insert(node);
        assertEquals(6, heap.size());
    }

    /**
     * Test of poll method, of class NodeHeap.
     */
    @Test
    public void PollRetrievesElementWithLowestPriority() {
        NodeHeap heap = new NodeHeap();
        Node node = new Node(15,18,2);
        Node node2 = new Node(12,11,0);
        Node node3 = new Node(11,15,4);
        heap.insert(node);
        heap.insert(node2);
        heap.insert(node3);
        
        Node poll = heap.poll();
        assertEquals(node2, poll);
    }
    
    @Test
    public void PollDecreasesSizeOfHeap() {
        NodeHeap heap = new NodeHeap();
        Node node = new Node(15,18,2);
        heap.insert(node);
        assertEquals(1,heap.size());
        heap.poll();
        assertEquals(0,heap.size());
    }
    
}
