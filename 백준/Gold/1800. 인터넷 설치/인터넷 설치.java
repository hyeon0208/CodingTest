import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, P, K;
    static List<Edge>[] graph;

    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 컴퓨터 수
        P = Integer.parseInt(st.nextToken()); // 케이블 선 수
        K = Integer.parseInt(st.nextToken()); // 공짜 케이블 수

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxCost = 0;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
            maxCost = Math.max(maxCost, c);
        }

        // 이분 탐색으로 최적의 비용 찾기 (비용 값 기반)
        int left = 0;
        int right = maxCost;
        int answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(mid)) {
                answer = mid;
                right = mid - 1; // 더 작은 비용 시도
            } else {
                left = mid + 1; // 더 큰 비용 시도
            }
        }

        System.out.println(answer);
    }

    // 최대 비용이 limit일 때 K개 이하의 간선을 무료로 사용하여 1->N 경로가 있는지 확인
    private static boolean isPossible(int limit) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] freeCount = new int[N + 1]; // 각 노드까지 가는데 필요한 무료 케이블의 최소 개수
        Arrays.fill(freeCount, Integer.MAX_VALUE);

        freeCount[1] = 0;
        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int count = curr[1];

            if (node == N) {
                return true;
            }

            if (count > freeCount[node]) {
                continue;
            }

            for (Edge edge : graph[node]) {
                int nextNode = edge.to;
                int nextCount = count + (edge.cost > limit ? 1 : 0);

                if (nextCount <= K && nextCount < freeCount[nextNode]) {
                    freeCount[nextNode] = nextCount;
                    pq.offer(new int[]{nextNode, nextCount});
                }
            }
        }

        return false;
    }
}