package ui;

import data_structures.MazeNodeList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Insets;
import java.text.NumberFormat;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;
import algorithms.A_star;
import algorithms.Dijkstra;
import algorithms.IDA_star;
import domain.Node;
import domain.TestResult;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import performance_test.PerformanceTest;

/**
 * A class containing the graphical user interface Needs a lot of cleanup
 */
public class Gui {

    private JFrame frame;
    private JPanel panel;
    private GridLayout grid;
    private int[][] maze;
    private GridButton[][] buttons;
    private String[] algorithms = {"Dijkstra", "A*", "IDA*"};
    private boolean selectingStart = false;
    private boolean selectingEnd = false;
    private Node start;
    private Node end;
    private final int GRIDSIZE = 40;
    private PerformanceTest performance;
    JLabel dijkstraTime = new JLabel("Dijkstra: ");
    JLabel aStarTime = new JLabel("A*: ");
    JLabel idaStarTime = new JLabel("IDA: ");

    public Gui() {
        this.frame = new JFrame("Pathfinding");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1100, 900);
        this.grid = new GridLayout(GRIDSIZE, GRIDSIZE);
        this.panel = new JPanel();
        this.panel.setLayout(grid);
        this.panel.setSize(800, 800);
        this.maze = new int[GRIDSIZE][GRIDSIZE];
        this.buttons = new GridButton[GRIDSIZE][GRIDSIZE];
        this.performance = new PerformanceTest();

        this.start = new Node(0, 0);
        this.end = new Node(0, 1);

        //Create top panel
        JPanel topPanel = new JPanel();

        JButton selectStart = new JButton("Select start point");
        JLabel startNode = new JLabel("Start: " + this.start.toString());
        topPanel.add(startNode);
        JButton selectEnd = new JButton("Select end point");
        JLabel endNode = new JLabel("End: " + this.end.toString());
        topPanel.add(endNode);

        selectStart.addActionListener(event -> {
            this.selectingStart = true;
            this.selectingEnd = false;
        });

        selectEnd.addActionListener(event -> {
            this.selectingEnd = true;
            this.selectingStart = false;
        });

        topPanel.add(selectStart);
        topPanel.add(selectEnd);

        JCheckBox wallCheckBox = new JCheckBox();
        topPanel.add(new JLabel("Add wall"));
        topPanel.add(wallCheckBox);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);

        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(Integer.MAX_VALUE - 1);
        formatter.setAllowsInvalid(false);

        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField nodeCost = new JFormattedTextField(formatter);
        nodeCost.setValue(1);
        nodeCost.setText(Integer.toString(1));

        topPanel.add(new JLabel("Node cost"));
        nodeCost.setColumns(4);
        topPanel.add(nodeCost);

        JComboBox algorithm = new JComboBox(algorithms);
        algorithm.setSelectedIndex(0);
        algorithm.addActionListener(algorithm);
        topPanel.add(new JLabel("Algorithm"));
        topPanel.add(algorithm);

        JButton runAlgorithm = new JButton("Run Algorithm");
        runAlgorithm.addActionListener(event -> {
            this.runSelectedAlgorithm(algorithm.getSelectedIndex());
        });

        topPanel.add(runAlgorithm);
        
        //Create right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        
        JLabel testOne = new JLabel();
        JLabel testOneDijkstra = new JLabel("");
        JLabel testOneAStar = new JLabel("");
        JLabel testOneIda = new JLabel("");
        
        JLabel testTwo = new JLabel("");
        JLabel testTwoDijkstra = new JLabel("");
        JLabel testTwoAStar = new JLabel("");
        JLabel testTwoIda = new JLabel("");
        
        JLabel testThree = new JLabel("");
        JLabel testThreeDijkstra = new JLabel("");
        JLabel testThreeAStar = new JLabel("");
        JLabel testThreeIda = new JLabel("");
        
        JButton performanceTests = new JButton("Test Performance");
        performanceTests.addActionListener(event -> {
            this.performance.RunTests();
            TestResult[] results = this.performance.getResults();
            
            testOneDijkstra.setText("Dijkstra average: " + results[0].getDijkstraAverage() + "ms");
            testOneAStar.setText("A* average: " + results[0].getAStarAverage()+ "ms");
            testOneIda.setText("IDA* average: " + results[0].getIdaAverage() + "ms");
            testOne.setText("Test 1: ");
            
            testTwoDijkstra.setText("Dijkstra average: " + results[1].getDijkstraAverage() + "ms");
            testTwoAStar.setText("A* average: " + results[1].getAStarAverage()+ "ms");
            testTwoIda.setText("IDA* average: " + results[1].getIdaAverage() + "ms");
            testTwo.setText("Test 2: ");

            testThreeDijkstra.setText("Dijkstra average: " + results[2].getDijkstraAverage() + "ms");
            testThreeAStar.setText("A* average: " + results[2].getAStarAverage()+ "ms");
            testThreeIda.setText("IDA* average: " + results[2].getIdaAverage() + "ms");
            testThree.setText("Test 3: ");
        });
        
        rightPanel.add(performanceTests);
        rightPanel.add(testOne);
        rightPanel.add(testOneDijkstra);
        rightPanel.add(testOneAStar);
        rightPanel.add(testOneIda);
        
        rightPanel.add(testTwo);
        rightPanel.add(testTwoDijkstra);
        rightPanel.add(testTwoAStar);
        rightPanel.add(testTwoIda);        
        
        rightPanel.add(testThree);
        rightPanel.add(testThreeDijkstra);
        rightPanel.add(testThreeAStar);
        rightPanel.add(testThreeIda);  

        JPanel bottomPanel = new JPanel();

        dijkstraTime = new JLabel("Dijkstra: ");
        aStarTime = new JLabel("A*: ");
        idaStarTime = new JLabel("IDA: ");

        bottomPanel.add(dijkstraTime);
        bottomPanel.add(aStarTime);
        bottomPanel.add(idaStarTime);

        this.frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        this.frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        this.frame.getContentPane().add(BorderLayout.EAST, rightPanel);

        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                maze[i][j] = 1;
            }
        }

        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[0].length; j++) {
                GridButton newButton = new GridButton("", i, j);

                int value = 1;
                String name;
                if (value == Integer.MAX_VALUE) {
                    name = "W";
                    newButton.setBackground(new Color(208, 40, 40));
                } else {
                    name = Integer.toString(maze[i][j]);
                }

                newButton.setText(name);
                newButton.setMargin(new Insets(1, 1, 1, 1));
                newButton.setBackground(Color.white);
                
                newButton.addActionListener(event
                        -> {
                    GridButton button = (GridButton) event.getSource();
                    if (this.selectingStart || this.selectingEnd) {
                        Color buttonColor;
                        if (this.selectingStart) {
                            buttonColor = Color.blue;
                            if (buttons[this.start.getY()][this.start.getX()].getBackground().equals(buttonColor)) {
                                this.resetColor(buttons[this.start.getY()][this.start.getX()]);
                            }
                            this.start = new Node(button.getCol(), button.getRow());
                            startNode.setText("Start: " + this.start.toString());
                            this.selectingStart = false;
                        } else {
                            buttonColor = Color.yellow;
                            if (buttons[this.end.getY()][this.end.getX()].getBackground().equals(buttonColor)) {
                                this.resetColor(buttons[this.end.getY()][this.end.getX()]);
                            }
                            this.end = new Node(button.getCol(), button.getRow());
                            endNode.setText("End: " + this.end.toString());
                            this.selectingEnd = false;
                        }

                        button.setBackground(buttonColor);
                    } else if (wallCheckBox.isSelected()) {
                        button.setText("W");
                        if (!button.getBackground().equals(Color.blue)) {
                            button.setBackground(new Color(208, 48, 48));
                        }
                        this.maze[button.getRow()][button.getCol()] = Integer.MAX_VALUE;
                    } else {
                        button.setText(nodeCost.getValue().toString());
                        if (!button.getBackground().equals(Color.blue)) {
                            button.setBackground(Color.white);
                        }
                        this.maze[button.getRow()][button.getCol()] = Integer.parseInt(nodeCost.getValue().toString());
                    }
                });

                this.panel.add(newButton);
                this.buttons[i][j] = newButton;
            }
        }
        this.frame.getContentPane().add(BorderLayout.CENTER, panel);
    }

    public void launch() {
        this.frame.setVisible(true);
    }

    private void resetColor(GridButton button) {
        if (button.getText().equals("W")) {
            button.setBackground(new Color(208, 48, 48));
        } else {
            button.setBackground(Color.WHITE);
        }
    }

    private void colorPathNode(Node node) {
        GridButton button = buttons[node.getY()][node.getX()];
        button.setBackground(Color.green);
    }

    private void runSelectedAlgorithm(int index) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                GridButton button = buttons[i][j];
                if (button.getBackground().equals(Color.green)) {
                    this.resetColor(buttons[i][j]);
                }
            }
        }

        String newLabel;

        if (index == 0) {
            Dijkstra dijkstra = new Dijkstra(this.maze);
            dijkstra.FindPath(this.start, this.end);
            MazeNodeList path = dijkstra.GetPath();

            for (int i = 0; i < path.size(); i++) {
                Node node = path.get(i);
                colorPathNode(node);
            }

            newLabel = "Dijkstra from ";

            newLabel += this.start.toString() + " to " + this.end.toString() + " completed in: ";
            newLabel += Long.toString(dijkstra.getDuration()) + " ns";
            dijkstraTime.setText(newLabel);
        } else if (index == 1) {
            A_star astar = new A_star(this.maze);
            astar.FindPath(this.start, this.end);
            MazeNodeList path = astar.GetPath();

            for (int i = 0; i < path.size(); i++) {
                Node node = path.get(i);
                colorPathNode(node);
            }

            newLabel = "A* from ";

            newLabel += this.start.toString() + " to " + this.end.toString() + " completed in: ";
            newLabel += Long.toString(astar.getDuration()) + " ns";
            aStarTime.setText(newLabel);
        } else if (index == 2) {
            IDA_star ida = new IDA_star(this.maze);
            ida.FindPath(this.start, this.end);
            MazeNodeList path = ida.GetPath();

            for (int i = 0; i < path.size(); i++) {
                Node node = path.get(i);
                colorPathNode(node);
            }

            newLabel = "IDA* from ";

            newLabel += this.start.toString() + " to " + this.end.toString() + " completed in: ";
            newLabel += Long.toString(ida.getDuration()) + " ns";
            idaStarTime.setText(newLabel);
        }
    }

}
