import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static long[] distance; // underflow를 고려한 long

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, weight));
        }

        distance = new long[N + 1]; // 시작 지점(1)에서 도착 지점(i)로 가는 거리
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[1] = 0;  // 1번에서 시작해서 1번으로 가는건 항상 거리가 0

        boolean hasNegativeCycle = false;

        // 밸만 포드는 음수 간선이 있을 수 있어서, 한 정점의 최단거리가 나중에 변경될 수 있음
        // 따라서 우선순위 큐를 사용하지 않고 모든 간선을 순서대로 처리
        for (int i = 1; i <= N; i++) {
            for (Edge edge : edges) {
                // Long.MAX_VALUE이면 경로가 없다는 것
                // 다음 도시(to)까지의 거리가 현재 도시를 거쳐가는 것보다 크다면
                if (distance[edge.from] != Long.MAX_VALUE && distance[edge.to] > distance[edge.from] + edge.weight) {
                    distance[edge.to] = distance[edge.from] + edge.weight; // 더 짧은 경로로 업데이트
                    // N번째 반복에서도 거리가 줄어든다면 음수 사이클 존재
                    if (i == N) {
                        hasNegativeCycle = true;
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (hasNegativeCycle) {
            sb.append(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                sb.append(distance[i] == Long.MAX_VALUE ? -1 : distance[i]).append('\n');
            }
        }
        System.out.print(sb);
    }

    private static class Edge {

        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
