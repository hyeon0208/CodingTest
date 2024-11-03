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

    private static final int INF = Integer.MAX_VALUE;

    private static int V;
    private static int E;
    private static List<List<Node>> graphs;
    private static int[] distance;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 정점 개수
        E = Integer.parseInt(st.nextToken()); // 간선 개수

        graphs = new ArrayList<>();
        distance = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            graphs.add(new ArrayList<>());
        }

        Arrays.fill(distance, INF);

        int K = Integer.parseInt(br.readLine()); // 시작 정점의 번호

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 시작 정점
            int v = Integer.parseInt(st.nextToken()); // 도착 정점
            int w = Integer.parseInt(st.nextToken()); // 간선의 가중치
            graphs.get(u).add(new Node(v, w));
        }

        dijkstra(K);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (i == K) {
                sb.append(0).append("\n");
                continue;
            }
            if (distance[i] == INF) {
                sb.append("INF").append("\n");
                continue;
            }
            sb.append(distance[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(value -> value.w));
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            int curDestination = curNode.v;
            int curCost = curNode.w;

            for (Node node : graphs.get(curDestination)) {
                int nextDestination = node.v;
                int nextCost = node.w + curCost;
                if (distance[nextDestination] > nextCost) {
                    distance[nextDestination] = nextCost;
                    pq.offer(new Node(nextDestination, nextCost));
                }
            }
        }
    }

    private static class Node {

        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
