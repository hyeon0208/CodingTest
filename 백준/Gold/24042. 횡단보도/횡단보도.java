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

    private static int N;
    private static int M;
    private static long[] dist;
    private static List<List<int[]>> roads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 지역 수
        M = Integer.parseInt(st.nextToken()); // 횡단보도의 주기

        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        roads = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            roads.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            roads.get(a).add(new int[]{b, i});
            roads.get(b).add(new int[]{a, i});
        }
        dijkstra(1);

        System.out.println(dist[N]);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.time));
        pq.offer(new Edge(0, start));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            for (int[] next : roads.get(current.node)) {
                int nextNode = next[0]; // 다음 노드 번호
                int lightOnTime = next[1]; // 다음 노드로 가는 신호등이 켜지는 시간

                long cycleTime = current.time % M; // 주기 내에서 현재 시간
                long waitTime = 0; // 다음 신호등이 켜질 떄까지 기다리는 시간
                if (cycleTime <= lightOnTime) {
                    waitTime = lightOnTime - cycleTime;
                } else {
                    waitTime = M - cycleTime + lightOnTime;
                }

                long nextTime = waitTime + current.time + 1; // 다음까지 총 소요되는 시간
                if (nextTime < dist[nextNode]) {
                    dist[nextNode] = nextTime;
                    pq.offer(new Edge(nextTime, nextNode));
                }
            }
        }
    }

    private static class Edge {
        long time;
        int node;

        Edge(long time, int node) {
            this.time = time;
            this.node = node;
        }
    }
}
