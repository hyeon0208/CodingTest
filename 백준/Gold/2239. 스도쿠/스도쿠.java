import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        solve(0);
    }

    private static boolean solve(int pos) {
        if(pos == 81) {
            printBoard();
            return true;
        }

        int row = pos / 9;
        int col = pos % 9;

        if(board[row][col] != 0) {
           return solve(pos + 1);
        }

        for(int num = 1; num <= 9; num++) {
            if(isValid(row, col, num)) {
                board[row][col] = num;
                if (solve(pos + 1)) {
                   return true;
                }
                board[row][col] = 0;
            }
        }

        return false;
    }

    private static boolean isValid(int row, int col, int num) {
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRow + i][boxCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}

