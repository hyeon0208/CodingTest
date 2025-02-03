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

    private static int INF = 200000000;
    private static List<List<Node>> graph;
    private static int N; // 정점 수
    private static int E; // 간선 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long path1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        long path2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        long answer = Math.min(path1, path2);

        if (answer >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static int dijkstra(int start, int end) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distance[cur.getV()] < cur.getCost()) {
                continue;
            }

            for (Node node : graph.get(cur.getV())) {
                if (distance[node.getV()] > distance[cur.getV()] + node.getCost()) {
                    distance[node.getV()] = distance[cur.getV()] + node.getCost();
                    pq.offer(new Node(node.getV(), distance[node.getV()]));
                }
            }
        }
        return distance[end];
    }

    private static class Node {
        private int v;
        private int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        public int getV() {
            return v;
        }

        public int getCost() {
            return cost;
        }
    }
}
