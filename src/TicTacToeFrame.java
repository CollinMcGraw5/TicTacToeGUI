import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {
    private TicTacToeButton[][] board = new TicTacToeButton[3][3];
    private String currentPlayer = "X";
    private int moveCount = 0;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        Font buttonFont = new Font("Arial", Font.BOLD, 48);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                TicTacToeButton btn = new TicTacToeButton(r, c);
                btn.setFont(buttonFont);
                btn.addActionListener(e -> handleMove(btn));
                board[r][c] = btn;
                gridPanel.add(btn);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(quitButton);

        add(gridPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(400, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleMove(TicTacToeButton btn) {
        if (!btn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Illegal move! Try again.");
            return;
        }

        btn.setValue(currentPlayer);
        moveCount++;

        if (moveCount >= 5 && checkWin(currentPlayer)) {
            JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
            promptRestart();
            return;
        }

        if (moveCount == 9) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            promptRestart();
            return;
        }

        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    private boolean checkWin(String player) {
        for (int r = 0; r < 3; r++)
            if (board[r][0].getValue().equals(player) &&
                    board[r][1].getValue().equals(player) &&
                    board[r][2].getValue().equals(player)) return true;

        for (int c = 0; c < 3; c++)
            if (board[0][c].getValue().equals(player) &&
                    board[1][c].getValue().equals(player) &&
                    board[2][c].getValue().equals(player)) return true;

        if (board[0][0].getValue().equals(player) &&
                board[1][1].getValue().equals(player) &&
                board[2][2].getValue().equals(player)) return true;

        if (board[0][2].getValue().equals(player) &&
                board[1][1].getValue().equals(player) &&
                board[2][0].getValue().equals(player)) return true;

        return false;
    }

    private void promptRestart() {
        int option = JOptionPane.showConfirmDialog(this, "Play again?", "Restart", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            for (int r = 0; r < 3; r++)
                for (int c = 0; c < 3; c++)
                    board[r][c].reset();
            currentPlayer = "X";
            moveCount = 0;
        } else {
            System.exit(0);
        }
    }
}