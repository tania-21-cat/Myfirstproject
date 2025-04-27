import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GamePage extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private Player player1, player2, currentPlayer;
    private JLabel turnLabel;

    public GamePage(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = player1;

        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Turn Label
        turnLabel = new JLabel(currentPlayer.getName() + "'s Turn (" + currentPlayer.getSymbol() + ")", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        turnLabel.setForeground(new Color(0, 102, 204));
        add(turnLabel, BorderLayout.NORTH);

        // Board Panel
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setBackground(new Color(220, 220, 220));
        add(boardPanel, BorderLayout.CENTER);

        Font buttonFont = new Font("Ink free", Font.BOLD, 40);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton("");
                button.setFont(buttonFont);
                button.setFocusPainted(false);
                button.setBackground(new Color(224, 242, 241));
                button.setForeground(Color.BLACK);
                int row = i, col = j;
                button.addActionListener(e -> handleMove(row, col));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        // Bottom Panel with Reset and Exit
        JPanel bottomPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");

        resetButton.setBackground(new Color(0, 153, 76));
        resetButton.setForeground(Color.BLUE);
        resetButton.setFont(new Font("Ink free", Font.BOLD, 16));

        exitButton.setBackground(new Color(204, 0, 0));
        exitButton.setForeground(Color.BLUE);
        exitButton.setFont(new Font("Ink free", Font.BOLD, 16));

        resetButton.addActionListener(e -> resetGame());
        exitButton.addActionListener(e -> System.exit(0));

        bottomPanel.add(resetButton);
        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleMove(int row, int col) {
        if (buttons[row][col].getText().equals("")) {
            buttons[row][col].setText(currentPlayer.getSymbol());
            currentPlayer.addMove(row, col);

            if (checkWin()) {
                setTitle(currentPlayer.getName() + " Wins!");
                // Show message and automatically reset if the player chooses to reset
                JOptionPane.showMessageDialog(this, currentPlayer.getName() + " Wins! Click Reset to play again.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            } else if (isDraw()) {
                setTitle("It's a Draw!");
                // Show message and automatically reset if the player chooses to reset
                JOptionPane.showMessageDialog(this, "It's a Draw! Click Reset to play again or exit", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            } else {
                switchPlayer();
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        turnLabel.setText(currentPlayer.getName() + "'s Turn (" + currentPlayer.getSymbol() + ")");
    }

    private boolean isDraw() {
        for (JButton[] row : buttons) {
            for (JButton btn : row) {
                if (btn.getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWin() {
        List<Point> moves = currentPlayer.getMoves();

        if (moves.size() < 3) return false;

        int[][][] winningCombos = {
                { {0,0}, {0,1}, {0,2} },
                { {1,0}, {1,1}, {1,2} },
                { {2,0}, {2,1}, {2,2} },
                { {0,0}, {1,0}, {2,0} },
                { {0,1}, {1,1}, {2,1} },
                { {0,2}, {1,2}, {2,2} },
                { {0,0}, {1,1}, {2,2} },
                { {0,2}, {1,1}, {2,0} }
        };

        for (int[][] combo : winningCombos) {
            boolean win = true;
            for (int[] cell : combo) {
                boolean found = false;
                for (Point move : moves) {
                    if (move.x == cell[0] && move.y == cell[1]) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        return false;
    }

    private void resetGame() {
        for (JButton[] row : buttons) {
            for (JButton btn : row) {
                btn.setText("");
            }
        }
        player1.resetMoves();
        player2.resetMoves();
        currentPlayer = player1;
        turnLabel.setText(currentPlayer.getName() + "'s Turn (" + currentPlayer.getSymbol() + ")");
        setTitle("Tic Tac Toe");
    }
}
