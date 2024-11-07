import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int order = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[N + 1];
        int[] visitedNumber = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            graph[i].sort(Collections.reverseOrder());
        }

        dfs(R, graph, visitedNumber);


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visitedNumber[i]).append('\n');
        }
        System.out.print(sb);
    }

    private static void dfs(int start, List<Integer>[] graph, int[] visitedNumber) {
        visitedNumber[start] = order++; // 방문 순서 기록

        for (int next : graph[start]) {
            if (visitedNumber[next] == 0) { // 아직 방문하지 않은 정점이면
                dfs(next, graph, visitedNumber);
            }
        }
    }
}
