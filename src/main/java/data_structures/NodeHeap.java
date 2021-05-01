package data_structures;

import domain.Node;

/**
 *
 * A min heap containing Node objects for use with pathfinding
 */
public class NodeHeap {

    Node[] heap;
    int size;

    /**
     * Constructor for new NodeHeap
     */
    public NodeHeap() {
        size = 0;
        heap = new Node[5];
    }

    /**
     *
     * @param node Node to insert into heap.
     */
    public void insert(Node node) {
        if (size == 0) {
            heap[0] = node;
            size++;
            return;
        }
        
        if (size == heap.length) {
            this.expandHeap();
        }
        
        heap[size] = node;
        int currentSize = size;
        size++;
        
        while (heap[currentSize].compareTo(heap[currentSize / 2]) < 0) {
            swap(currentSize, currentSize/2);
            currentSize = currentSize/2;
        }
        
        
    }

    /**
     * Expands the current heap to fit more elements.
     */
    private void expandHeap() {
        heap = copyArray();
    }
    
    /**
     * Returns a copy of the heap with twice the capacity of the current one.
     */ 
    private Node[] copyArray() {
        Node[] newArray = new Node[heap.length * 2];
        for (int i = 0; i < heap.length; i++) {
            newArray[i] = heap[i];
        }
        return newArray;
    }
    
    /**
     * Converts the current Node array into a min heap.
     * @param index position to heapify
     */ 
    private void heapify(int index) {
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        if (rightIndex < size || leftIndex < size) {
            if (heap[index].compareTo(heap[leftIndex]) > 0 
                || heap[index].compareTo(heap[rightIndex]) > 0){
                if (heap[leftIndex].compareTo(heap[rightIndex]) < 0) {
                swap(index, leftIndex);
                heapify(leftIndex);
                }
                
                else {
                    swap(index, rightIndex);
                    heapify(rightIndex);
                }
                
            }
            
        }
        
    }

     /**
     * Swaps two elements in the heap.
     * @param index1 Position of first element.
     * @param index2 Position of second element.
     */ 
    private void swap(int index1, int index2) {
        Node temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
    
    /**
     * Returns the lowest priority node in the heap.
     * @return Node with lowest priority.
     */
    public Node poll() {
        if (size == 0) {
            return null;
        }
        
        Node top = heap[0];
        size --;
        heap[0] = heap[size];
        heapify(0);
        return top;
    }
    
    /**
     * Returns the size of the heap.
     * @return Current heap size.
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns the size of the heap.
     * @return Current heap size.
     */
    public Node peekLast() {
        if (size == 0) {
            return null;
        }
        return heap[size - 1];
    }

     /**
     * Checks if heap contains specified node.
     * TODO: Optimize search
     * @return True if node is in heap, false if not.
     */
    public boolean contains(Node node) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(node)) {
                return true;
            }
        }
        return false;
    }
     /**
     * Adds node to the end of the array.
     */  
    public void add(Node node) {
        if (size == heap.length) {
            this.expandHeap();
        }
        heap[size] = node;
        size++;
    }
    
    /**
     * Retrieves the last element
     * @return The last node in the array.
     */
    public Node pollLast() {
        if (size == 0) {
            return null;
        }
        
        Node poll = heap[size - 1];
        heap[size - 1] = null;
        size --;
        
        return poll;
    }
}
