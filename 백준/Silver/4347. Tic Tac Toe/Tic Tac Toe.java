import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            char[][] board = new char[3][3];
            for (int i = 0; i < 3; i++) {
                String line = br.readLine();
                board[i] = line.toCharArray();
            }

            if (isValid(board)) {
                sb.append("yes");
            } else {
                sb.append("no");
            }

            if (N > 0) {
                br.readLine();
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    /*
    - X가 항상 먼저 시작
    - X가 이기면 O수 보다 하나 더 많다
    - O가 이기면 X수와 동일하다
     */
    private static boolean isValid(char[][] board) {
        int countX = countStone(board, 'X');
        int countO = countStone(board, 'O');

        if (countO > countX || countX > countO + 1) {
            return false;
        }

        if (hasWin(board, 'X') && hasWin(board, 'O')) {
            return false;
        }

        if (hasWin(board, 'X')) {
            return countX == countO + 1;
        }

        if (hasWin(board, 'O')) {
            return countX == countO;
        }

        return true;
    }

    private static int countStone(char[][] board, char stone) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == stone) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean hasWin(char[][] board, char stone) {
        // 가로 확인
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == stone && board[i][1] == stone && board[i][2] == stone) {
                return true;
            }
        }

        // 세로 확인
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == stone && board[1][j] == stone && board[2][j] == stone) {
                return true;
            }
        }

        // 대각선 확인 (좌상->우하)
        if (board[0][0] == stone && board[1][1] == stone && board[2][2] == stone) {
            return true;
        }

        // 대각선 확인 (우상->좌하)
        if (board[0][2] == stone && board[1][1] == stone && board[2][0] == stone) {
            return true;
        }

        return false;
    }
}
