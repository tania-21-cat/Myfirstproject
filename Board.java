public class Board {
    private String[][] board;
    private int size = 3; // 3X3 grid

    public Board() {
        board = new String[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "";
            }
        }
    }

    public boolean makeMove(int row, int col, String symbol) {
        if (board[row][col].equals("")) {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    public boolean checkWin(String symbol) {
        // Row check
        for (int i = 0; i < size; i++) {
            if (board[i][0].equals(symbol) && board[i][1].equals(symbol) && board[i][2].equals(symbol)) {
                return true;
            }
        }
        // Column check
        for (int i = 0; i < size; i++) {
            if (board[0][i].equals(symbol) && board[1][i].equals(symbol) && board[2][i].equals(symbol)) {
                return true;
            }
        }
        // Diagonal check
        if (board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(symbol)) {
            return true;
        }
        if (board[0][2].equals(symbol) && board[1][1].equals(symbol) && board[2][0].equals(symbol)) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}
