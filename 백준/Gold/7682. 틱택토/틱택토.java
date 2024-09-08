import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static char[][] board = new char[3][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line.equals("end")) {
                break;
            }
            int xCnt = 0;
            int oCnt = 0;
            int stoneIndex = 0;
            char[] stones = line.toCharArray();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char stone = stones[stoneIndex++];
                    if (stone == 'X') {
                        xCnt++;
                    } else if (stone == 'O') {
                        oCnt++;
                    }
                    board[i][j] = stone;
                }
            }

            if (xCnt == oCnt + 1) {
                if (xCnt + oCnt == 9 && !bingo('O')) {
                    sb.append("valid").append("\n");
                } else if (bingo('X') && !bingo('O')) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            } else if (xCnt == oCnt) {
                if (bingo('O') && !bingo('X')) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            } else {
                sb.append("invalid").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean bingo(char stone) {
        // 가로
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == stone && board[i][1] == stone && board[i][2] == stone) {
                return true;
            }
        }
        // 세로
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == stone && board[1][i] == stone && board[2][i] == stone) {
                return true;
            }
        }
        // 대각선
        if (board[0][0] == stone && board[1][1] == stone && board[2][2] == stone) {
            return true;
        }

        if (board[0][2] == stone && board[1][1] == stone && board[2][0] == stone) {
            return true;
        }
        return false;
    }
}
