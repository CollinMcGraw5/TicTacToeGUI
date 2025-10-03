import javax.swing.*;

public class TicTacToeButton extends JButton {
    private int row;
    private int col;
    private String value = "";

    public TicTacToeButton(int row, int col) {
        this.row = row;
        this.col = col;
        setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 48));
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public String getValue() { return value; }

    public void setValue(String val) {
        value = val;
        setText(val);
    }

    public boolean isEmpty() {
        return value.equals("");
    }

    public void reset() {
        value = "";
        setText("");
    }
}