import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final char MY_TEAM = 'W';
    private static final char ENEMY = 'B';
    private static int[] DX = {-1, 1, 0, 0};
    private static int[] DY = {0, 0, -1, 1};
    private static int N;
    private static int M;
    private static char[][] army;
    private static boolean[][] visited;
    private static int whiteSum, blueSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 세로 = 행, 가로 = 열
        army = new char[M][N];
        visited = new boolean[M][N];
        for (int x = 0; x < M; x++) {
            String line = br.readLine();
            for (int y = 0; y < N; y++) {
                army[x][y] = line.charAt(y);
            }
        }

        for (int x = 0; x < M; x++) {
            for (int y = 0; y < N; y++) {
                if (!visited[x][y]) {
                    int count = dfs(x, y, army[x][y]);
                    if (army[x][y] == MY_TEAM) {
                        whiteSum += count * count;
                    }
                    if (army[x][y] == ENEMY) {
                        blueSum += count * count;
                    }
                }
            }
        }

        System.out.println(whiteSum + " " + blueSum);
    }

    private static int dfs(int x, int y, char team) {
        visited[x][y] = true;
        int count = 1;

        for (int i = 0; i < 4; i++) {
            int nextX = x + DX[i];
            int nextY = y + DY[i];

            if (0 <= nextX && nextX < M && 0 <= nextY && nextY < N && !visited[nextX][nextY]
                    && army[nextX][nextY] == team) {
                count += dfs(nextX, nextY, team);
            }
        }

        return count;
    }
}
