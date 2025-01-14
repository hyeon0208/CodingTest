import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int N;
    private static int M;
    private static int result;
    private static int[][] maps;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maps = new int[N][M];
        visited = new boolean[N][M];

        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                maps[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                visited[x][y] = true;
                dfs(x, y, 1, maps[x][y]);
                checkSpecial(x, y, 1, 0, maps[x][y]);
                visited[x][y] = false;
            }
        }

        System.out.println(result);
    }

    // DFS로 테트로미노 만들기 (ㅗ 모양 제외)
    private static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            result = Math.max(result, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + DX[d];
            int ny = y + DY[d];

            if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + maps[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    // ㅗ, ㅓ, ㅏ, ㅜ 모양 확인 (따로 처리)
    private static void checkSpecial(int x, int y, int depth, int startDirection, int sum) {
        if (depth == 4) {
            result = Math.max(result, sum);
            return;
        }

        // 현재 위치에서 상하좌우 순차적으로 탐색
        for (int d = startDirection; d < 4; d++) {
            int nx = x + DX[d];
            int ny = y + DY[d];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                checkSpecial(x, y, depth + 1, d + 1, sum + maps[nx][ny]);
            }
        }
    }
}
