import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    private static int N;
    private static int K;
    private static int[][] distance;
    private static boolean[] visited;
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        distance = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                distance[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드 워셜로 각 행성 간의 최소 이동 시간 구하기
        for (int k = 0; k < N; k++) { // 경유지
            for (int i = 0; i < N; i++) { // 출발지
                for (int j = 0; j < N; j++) { // 도착지
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        
        visited = new boolean[N];
        visited[K] = true; 
        dfs(K, 1, 0);
        System.out.println(result);
    }

    // DFS로 최단 경로 거리 찾음
    private static void dfs(int start, int depth, int total) {
        if (depth == N) {
            result = Math.min(result, total);
            return;
        }

        for (int next = 0; next < N; next++) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, depth + 1, distance[start][next] + total);
                visited[next] = false;
            }
        }
    }
}
