import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/9370

    private static int n;
    private static int m;
    private static int t;
    private static int s;
    private static int g;
    private static int h;
    private static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 교차로 (정점)
            m = Integer.parseInt(st.nextToken()); // 도로 (간선)
            t = Integer.parseInt(st.nextToken()); // 목적지 후보의 개수

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); // 출발지
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            // g와 h 교차로 사이에 있는 도로를 지나갔다

            graph = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            int ghDistance = 0; // gh 간선 가중치
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()); // 가중치
                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));

                if ((a == g && b == h) || (a == h && b == g)) {
                    ghDistance = d;
                }
            }

            int[] candinates = new int[t + 1];
            for (int i = 1; i <= t; i++) {
                candinates[i] = Integer.parseInt(br.readLine());
            }

            int[] direct = dijkstra(s);
            int[] fromG = dijkstra(g); // 출발지 ~ G까지 최단 거리
            int[] fromH = dijkstra(h); // 출발지 ~ H까지 최단 거리

            List<Integer> results = new ArrayList<>();
            for (int i = 1; i <= t; i++) {
                int directDistance = direct[candinates[i]];
                int path1 = direct[g] + ghDistance + fromH[candinates[i]]; // s→g→h→목적지
                int path2 = direct[h] + ghDistance + fromG[candinates[i]]; // s→h→g→목적지

                int minGHPath = Math.min(path1, path2);

                if (directDistance == minGHPath) {
                    results.add(candinates[i]);

                }
            }
            results.sort(null);
            for (Integer result : results) {
                sb.append(result).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb); // 주어진 목적지 후보들 중 불가능한 경우들을 제외한 목적지들을 공백으로 분리시킨 오름차순의 정수
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (distance[cur.v] < cur.cost) {
                continue;
            }
            for (Node nextNode : graph.get(cur.v)) {
                int nextCost = cur.cost + nextNode.cost;
                if (distance[nextNode.v] > nextCost) {
                    distance[nextNode.v] = nextCost;
                    pq.offer(new Node(nextNode.v, nextCost));
                }
            }
        }

        return distance;
    }

    private static class Node {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}
