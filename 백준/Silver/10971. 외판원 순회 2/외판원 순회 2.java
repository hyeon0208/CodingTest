import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] visited;
    static int[][] map;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, i, 0, 0);
        }
        System.out.println(result);
    }

    public static void dfs(int start, int now, int cost, int depth) {
        if (depth == N - 1) { // 출발한 도시는 방문처리하기 때문에 해당 도시를 제외 N - 1
            if (map[now][start] != 0) {
                result = Math.min(result, cost + map[now][start]); // 출발지로 가기 위한 비용 계산
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && map[now][i] != 0) {
                visited[i] = true;
                dfs(start, i, cost + map[now][i], depth + 1);
                visited[i] = false;
            }
        }
    }
}
