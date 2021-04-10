package pathfinding.pathfinding_comparisons;

import java.util.Objects;

/**
 * <h1>Node</h1>
 * A class representing a point in a 2-dimensional array.
 * 
 * A node has an X and a Y coordinate, a priority value used for sorting and a
 * link to another node, used in Dijkstra's algorithm to construct the shortest
 * path by finding the previous node on the path.
 */
public class Node implements Comparable<Node> {
    Integer x;
    Integer y;
    int priority;
    Node previous;
    
    Node(int x, int y, int priority) {
        this.x = x;
        this.y = y;
        this.priority = priority;
    }

    Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.priority = 0;
    }

     /**
     * @return The node's X coordinate.
     */
    public int getX() {
        return this.x;
    }

     /**
     * @return The node's Y coordinate.
     */
    public int getY() {
        return this.y;
    }

     /**
     * @return The node's priority value.
     */
    public int getPriority() {
        return this.priority;
    }

     /**
     * Set the node's X value.
     * @param x The new X value of the node.
     */
    public void setX(int x) {
        this.x = x;
    }

     /**
     * Set the node's Y value.
     * @param y The new Y value of the node.
     */
    public void setY(int y) {
        this.y = y;
    }

     /**
     * Set the node's priority value.
     * @param priority The new priority value of the node.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

     /**
     * @return The node preceding this node on the path.
     */   
    public Node getPrevious() {
        return this.previous;
    }

     /**
     * Set the node's previous node.
     * @param node The node preceding this node on the path.
     */    
    public void setPrevious(Node node) {
        this.previous = node;
    }

     /**
     * Comparator to compare nodes. Nodes are sorted by their priority.
     * @param o Node to compare this node to.
     * @return Result of the comparison.
     */   
    @Override
    public int compareTo(Node o) {
        return this.priority - o.getPriority();
    }

     /**
     * @return String representation of the node in the format (x,y).
     */ 
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")"; 
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        else {
            Node node = (Node) o;
            if (this.x == node.getX() && this.y == node.getY()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean equals(Node node) {
        if (node.getX() == this.x && node.getY() == this.y) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        int result = 3;
        result = 7 * result + this.x.hashCode();
        result = 7 * result + this.y.hashCode();
        return result;
    }
}
