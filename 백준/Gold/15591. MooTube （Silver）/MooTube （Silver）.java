import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int[] DX = {0, 1, 0, -1};
    private static int[] DY = {1, 0, -1, 0};

    private static int N;
    private static int Q;
    private static int L;
    private static Map<Integer, Character> directions = new HashMap<>();

    private static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            graph.get(p).add(new Node(q, r));
            graph.get(q).add(new Node(p, r));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(bfs(k, v)).append('\n');
        }

        System.out.print(sb);
    }

    private static int bfs(int k, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        boolean[] visited = new boolean[N + 1];
        int count = 0;
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Node node : graph.get(cur)) {
                if (!visited[node.to] && node.usado >= k) {
                    visited[node.to] = true;
                    queue.offer(node.to);
                    count++;
                }
            }
        }

        return count;
    }

    private static class Node {
        int to;
        int usado;

        Node(int to, int usado) {
            this.to = to;
            this.usado = usado;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", usado=" + usado +
                    '}';
        }
    }
}
