package performance_test;

import algorithms.A_star;
import algorithms.Dijkstra;
import algorithms.IDA_star;
import domain.Node;

/**
 * A class for testing performance on large mazes
 */
public class PerformanceTest {

    int W = Integer.MAX_VALUE;
    public TestResult[] testResults = new TestResult[3];

    public void RunTests() {
        int[][] testMaze= new int[6000][6000];
        Node start = new Node(0, 151);
        Node destination = new Node(3999, 150);

        for (int i = 0; i < 6000; i++) {
            for (int j = 0; j < 6000; j++) {
                if (i >= 150 && i < 152) {
                    testMaze[i][j] = 1;
                } else {
                    testMaze[i][j] = W;
                }
            }
        }

        System.out.println("Running tests with large maze and straight line: ");
        TestResult result = this.Test(testMaze, start, destination);
        
        this.testResults[0] = result;
        testMaze = new int[6000][6000];

        for (int i = 0; i < 6000; i++) {
            for (int j = 0; j < 6000; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        testMaze[i][j] = W;
                    }
                } else {
                    testMaze[i][j] = 1;
                }
            }
        }
        start = new Node(1999, 1999);
        destination = new Node(3999, 3999);

        System.out.println("");
        System.out.println("Running tests with large checkerboard maze: ");
        result = this.Test(testMaze, start, destination);
        this.testResults[1] = result;

        testMaze = new int[6000][6000];

        for (int i = 0; i < 6000; i++) {
            for (int j = 0; j < 6000; j++) {
                if (i == j) {
                    testMaze[i][j] = W;
                } else {
                    testMaze[i][j] = 1;
                }
            }
        }

        testMaze[2999][2999] = 1;

        start = new Node(0, 1);
        destination = new Node(5999, 5998);

        System.out.println("");
        System.out.println("Running tests with large maze with a diagonal divide down the middle and a single gap: ");

        result = this.Test(testMaze, start, destination);
        this.testResults[2] = result;
    }

    private TestResult Test(int[][] maze, Node start, Node destination) {
        for (int i = 0; i < 6000; i++) {
            for (int j = 0; j < 6000; j++) {
                if (i == 150) {
                    maze[i][j] = 1;
                } else {
                    maze[i][j] = W;
                }
            }
        }

        Dijkstra dijkstra = new Dijkstra(maze);
        A_star astar = new A_star(maze);
        IDA_star ida = new IDA_star(maze);

        long dijkstraDuration = 0;
        long astarDuration = 0;
        long idaDuration = 0;

        for (int i = 0; i < 5; i++) {
            dijkstra.FindPath(start, destination);
            dijkstraDuration += dijkstra.getDuration();

            astar.FindPath(start, destination);
            astarDuration += astar.getDuration();

            ida.FindPath(start, destination);
            idaDuration += ida.getDuration();
        };

        System.out.println("Dijkstra average: " + dijkstraDuration / 5000000 + " ms");
        System.out.println("A* average: " + astarDuration / 5000000 + " ms");
        System.out.println("IDA* average: " + idaDuration / 5000000 + " ms");

        TestResult result = new TestResult(dijkstraDuration / 5000000, astarDuration / 5000000, idaDuration / 5000000);
        return result;
    }

    public TestResult[] getResults() {
        return this.testResults;
    }
}
