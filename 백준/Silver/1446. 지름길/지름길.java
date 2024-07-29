import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= D; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < D; i++) {
            graph.get(i).add(new Edge(i + 1, 1));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            if (start > D || end > D) {
                continue;
            }
            graph.get(start).add(new Edge(end, length));
        }

        int[] distance = new int[D + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int curEnd = current.end;

            if (distance[curEnd] < current.cost) {
                continue;
            }

            for (Edge next : graph.get(curEnd)) {
                if (distance[next.end] > distance[curEnd] + next.cost) {
                    distance[next.end] = distance[curEnd] + next.cost;
                    pq.offer(new Edge(next.end, distance[next.end]));
                }
            }
        }

        System.out.println(distance[D]);
    }

    private static class Edge implements Comparable<Edge> {
        int end, cost;

        Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
