import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/2186

    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    private static String word;
    private static int k;
    private static int n;
    private static int m;
    private static char[][] maps;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        maps = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                maps[i][j] = line.charAt(j);
            }
        }

        word = br.readLine();
        dp = new int[n][m][word.length()]; // 현재 위치 (x, y)에서 문자열 word의 depth번째 문자부터 시작하여 끝까지 완성할 수 있는 경우의 수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], -1); // -1로 초기화하여 미방문 상태 표시
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i][j] == word.charAt(0)) {
                    result += dfs(1, i, j);
                }
            }
        }
        System.out.println(result);
    }

    private static int dfs(int depth, int x, int y) {
        if (depth == word.length()) {
            return 1;
        }

        if (dp[x][y][depth] != -1) { // 이미 계산된 상태라면 저장된 값 반환
            return dp[x][y][depth];
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            for (int move = 1; move <= k; move++) {
                int nx = x + DX[i] * move;
                int ny = y + DY[i] * move;

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (maps[nx][ny] == word.charAt(depth)) {
                        result += dfs(depth + 1, nx, ny);
                    }
                }
            }
        }
        return dp[x][y][depth] = result;
    }
}
