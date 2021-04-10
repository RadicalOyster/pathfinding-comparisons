package pathfinding.pathfinding_comparisons;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class IDA_star {

    int[][] maze;
    int[][] distance;
    boolean pathFound;
    Node currentNode;
    int fVAlue;
    ArrayDeque<Node> path;

    public int ManhattanDistance(Node node1, Node node2) {
        return Math.abs(node1.getX() - node2.getX()) + Math.abs(node1.getY() - node2.getY());
    }

    public int estimate_distance(Node node, Node goal) {
        return this.maze[node.getY()][node.getX()] + ManhattanDistance(node, goal);
    }
    
    public boolean IsValid(Node node) {
        return (node.getY() >= 0 && node.getY() < this.maze.length) && (node.getX() >= 0 && node.getX() < this.maze[0].length);
    }
    
    public PriorityQueue<Node> Successors(Node node) {
        PriorityQueue<Node> successors = new PriorityQueue<>();
        for (int i = 0; i < 4; i++) {
            Node newNode = new Node(node.getX(), node.getY(), 0);

            switch (i) {
                case 0:
                    newNode.setX(newNode.getX() + 1);
                    break;
                case 1:
                    newNode.setX(newNode.getX() - 1);
                    break;
                case 2:
                    newNode.setY(newNode.getY() + 1);
                    break;
                default:
                    newNode.setY(newNode.getY() - 1);
                    break;
            }
            
            if (IsValid(newNode)) {
                newNode.setPriority(node.priority + this.maze[newNode.getY()][newNode.getX()]);
                successors.add(newNode);
            }
        }
        return successors;
    }
    
    public void FindPath(Node start, Node destination) {
        this.distance[start.getY()][start.getX()] = 0;
        int threshold = ManhattanDistance(start, destination);
        this.path.push(start);
        while (true) {
            System.out.println("Threshold at " + threshold);
            threshold = this.Search(0, threshold, destination);
            if (threshold == -1) {
                System.out.println("No path found");
                break;
            }
        }
    }
    
    public int Search(int estimatedDistance, int threshold, Node destination) {
        Node currentNode = this.path.peek();
        
        int current_estimate = estimatedDistance + ManhattanDistance(currentNode, destination);
        
        if (current_estimate > threshold) {
            return current_estimate;
        }

        int min = Integer.MAX_VALUE;
        PriorityQueue<Node> successors = this.Successors(currentNode);
        
        while (successors.size() > 0) {
            Node successor = successors.poll();
            
        }
        
        return 0;
    }
}
