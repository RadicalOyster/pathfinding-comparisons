package ui;

import javax.swing.JButton;

/**
 * A custom JButton that stores its position.
 */
public class GridButton extends JButton {
    private int row;
    private int col;
    
    public GridButton (String text, int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getCol() {
        return this.col;
    }
}
