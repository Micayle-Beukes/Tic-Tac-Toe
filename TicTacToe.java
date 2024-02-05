import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn. Enter row (0-2) and column (0-2) separated by space:");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                makeMove(row, col);

                if (isWinner()) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }

                if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }

                switchPlayer();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    private static void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  -----");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean isWinner() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true; // Check rows
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true; // Check columns
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true; // Check diagonal (top-left to bottom-right)
        }
        return board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer; // Check diagonal (top-right to bottom-left)
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // There is an empty space, the board is not full
                }
            }
        }
        return true; // All spaces are filled, it's a draw
    }
}
