import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private char[][] board = new char[4][4];

    public TicTacToe() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void printBoard() {
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("|          " + board[i][j] + "         ");
            }
            System.out.println("|");
            System.out.println("----------------------------------------------------------------");
        }
    }

    public boolean isMovesLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    public int minimax(int depth, boolean isMax) {
        int score = evaluate();

        if (score == 10) {
            return score;
        }
        if (score == -10) {
            return score;
        }
        if (!isMovesLeft()) {
            return 0;
        }

        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = PLAYER_O;
                        best = Math.max(best, minimax(depth + 1, false));
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = PLAYER_X;
                        best = Math.min(best, minimax(depth + 1, true));
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;
        }
    }

    public Move findBestMove() {
        int bestVal = -1000;
        Move bestMove = new Move(-1, -1);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = PLAYER_O;
                    int moveVal = minimax(0, false);
                    board[i][j] = EMPTY;
                    System.out.println("Move: " + i + " " + j + " Value: " + moveVal);
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    public static void main(String[] args) {
        TicTacToe tictactoe = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (tictactoe.isMovesLeft()) {
            tictactoe.printBoard();
            System.out.println("Enter your move (row[0-3] col[0-3]): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            tictactoe.makeMove(row, col, PLAYER_X);
            Move bestMove = tictactoe.findBestMove();
            tictactoe.makeMove(bestMove.row, bestMove.col, PLAYER_O);
        }

        tictactoe.printBoard();
        System.out.println("Game over.");
        scanner.close();
    }

    private boolean makeMove(int row, int col, char player) {
        if (row >= 0 && row < 4 && col >= 0 && col < 4 && board[row][col] == EMPTY) {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    static class Move {
        int row, col;

        Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
