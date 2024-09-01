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
    private static List<List<Node>> graph = new ArrayList<>();
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        distance = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        dijkstra(1);
        System.out.println(distance[N]);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.cost)));
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentV = current.v;
            int currentCost = current.cost;
            if (distance[currentV] < currentCost) {
                continue;
            }
            for (int i = 0; i < graph.get(currentV).size(); i++) {
                int nextCost = distance[currentV] + graph.get(currentV).get(i).cost;

                if (nextCost < distance[graph.get(currentV).get(i).v]) {
                    distance[graph.get(currentV).get(i).v] = nextCost;
                    pq.add(new Node(graph.get(currentV).get(i).v, nextCost));
                }
            }
        }
    }

    private static class Node {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}
