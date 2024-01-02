import java.io.*;
import java.util.*;

public class Main {
    private static Set<String> results = new HashSet<>();
    private static String[][] board = new String[5][5];
    private static int[] dx = new int[]{1, -1, 0, 0};
    private static int[] dy = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = st.nextToken();
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 0, board[i][j]);
            }
        }
        System.out.println(results.size());
    }

    private static void dfs(int x, int y, int depth, String number) {
        if (depth == 5) {
            results.add(number);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if ((0 <= nextX && nextX < 5) && (0 <= nextY && nextY < 5)) {
                dfs(nextX, nextY, depth + 1, number + board[nextX][nextY]);
            }
        }
    }
}