import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/1956

    private static final int INF = Integer.MAX_VALUE / 2;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] distance = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i != j) { // 자기 자신이 아닌 경우
                    distance[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            distance[a][b] = c;
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        int result = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                // 자기 자신을 제외한 두 정점이 서로에게 가는 경로가 있다면, 사이클이 존재한다는 뜻.
                if (i != j && distance[i][j] != INF && distance[j][i] != INF) {
                    result = Math.min(result, distance[i][j] + distance[j][i]);
                }
            }
        }
        System.out.println(result == INF ? -1 : result);
    }
}
