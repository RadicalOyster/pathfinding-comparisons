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
import pathfinding.pathfinding_comparisons.A_star;
import pathfinding.pathfinding_comparisons.Dijkstra;
import pathfinding.pathfinding_comparisons.IDA_star;
import pathfinding.pathfinding_comparisons.Node;

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
    JLabel dijkstraTime = new JLabel("Dijkstra: ");
    JLabel aStarTime = new JLabel("A*: ");
    JLabel idaStarTime = new JLabel("IDA: ");

    public Gui() {
        this.frame = new JFrame("Pathfinding");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(900, 900);
        this.grid = new GridLayout(GRIDSIZE, GRIDSIZE);
        this.panel = new JPanel();
        this.panel.setLayout(grid);
        this.maze = new int[GRIDSIZE][GRIDSIZE];
        this.buttons = new GridButton[GRIDSIZE][GRIDSIZE];

        this.start = new Node(0, 0);
        this.end = new Node(0, 1);

        JPanel test = new JPanel();

        JButton selectStart = new JButton("Select start point");
        JLabel startNode = new JLabel("Start: " + this.start.toString());
        test.add(startNode);
        JButton selectEnd = new JButton("Select end point");
        JLabel endNode = new JLabel("End: " + this.end.toString());
        test.add(endNode);

        selectStart.addActionListener(event -> {
            this.selectingStart = true;
            this.selectingEnd = false;
        });

        selectEnd.addActionListener(event -> {
            this.selectingEnd = true;
            this.selectingStart = false;
        });

        test.add(selectStart);
        test.add(selectEnd);

        JCheckBox wallCheckBox = new JCheckBox();
        test.add(new JLabel("Add wall"));
        test.add(wallCheckBox);

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

        test.add(new JLabel("Node cost"));
        nodeCost.setColumns(4);
        test.add(nodeCost);

        JComboBox algorithm = new JComboBox(algorithms);
        algorithm.setSelectedIndex(0);
        algorithm.addActionListener(algorithm);
        test.add(new JLabel("Algorithm"));
        test.add(algorithm);

        JButton runAlgorithm = new JButton("Run Algorithm");
        runAlgorithm.addActionListener(event -> {
            this.runSelectedAlgorithm(algorithm.getSelectedIndex());
        });

        test.add(runAlgorithm);
        
        JPanel test2 = new JPanel();
        
        dijkstraTime = new JLabel("Dijkstra: ");
        aStarTime = new JLabel("A*: ");
        idaStarTime = new JLabel("IDA: ");
        
        test2.add(dijkstraTime);
        test2.add(aStarTime);
        test2.add(idaStarTime);
        
        this.frame.getContentPane().add(BorderLayout.NORTH, test);
        this.frame.getContentPane().add(BorderLayout.SOUTH, test2);

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
        }
        
        else if (index == 1) {
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
        }
        
        else if (index == 2) {
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