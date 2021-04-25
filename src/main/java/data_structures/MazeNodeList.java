package data_structures;


import pathfinding.pathfinding_comparisons.Node;

/**
* A list object for storing nodes.
*/ 
public class MazeNodeList {
    Node[] list;
    int size;
    
    /**
     * Constructor for MazeNodeList.
     */
    public MazeNodeList() {
        list = new Node[5];
        size = 0;
    }
    
    /**
     * Adds a node to the end of the list.
     * @param node Node to add.
     */
    public void add(Node node) {
        if (size == list.length) {
            expandList();
        }
        
        list[size] = node;
        size++;
    }
    
    /**
     * Expands the current list to fit more elements.
     */
    private void expandList() {
        list = copyArray();
    }
    
    /**
     * Returns a copy of the list with twice the capacity of the current one.
     */ 
    private Node[] copyArray() {
        Node[] newArray = new Node[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            newArray[i] = list[i];
        }
        return newArray;
    }
    
    /**
     * Reverses the order of elements in the list.
     */
    public void reverse() {
        Node[] reversedList = new Node[list.length];
        for (int i = size - 1; i >= 0; i--) {
            reversedList[i] = list[size - 1 - i];
        }
        this.list = reversedList;
    }

     /**
     * Returns element at given index.
     * @param i Index of element to retrieve.
     * @return Element at index i. If element is out of range, return null.
     */
    public Node get(int i) {
        if (i >= size || i < 0) {
            return null;
        }
        return list[i];
    }
    
    @Override
    public String toString() {
        String string = "[";
        for (int i = 0; i < size - 1; i++) {
            string += list[i] + ", ";
        }
        string += list[size - 1] + "]";
        return string;
    }
    
     /**
     * Returns the size of the list.
     * @param Size of the list.
     */ 
    public int size() {
        return size;
    }
}
