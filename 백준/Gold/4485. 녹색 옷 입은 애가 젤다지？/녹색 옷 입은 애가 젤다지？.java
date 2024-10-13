import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int count = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            int[][] maps = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append(String.format("Problem %d: %d", count, dijkstra(maps))).append("\n");
            count++;
        }

        System.out.println(sb);
    }

    private static int dijkstra(int[][] maps) {
        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        pq.offer(new Node(0, 0, maps[0][0]));
        dist[0][0] = maps[0][0];

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.x == N - 1 && current.y == N - 1) {
                return current.cost;
            }

            if (dist[current.x][current.y] < current.cost) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + DX[i];
                int ny = current.y + DY[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                int nextCost = current.cost + maps[nx][ny];
                if (nextCost < dist[nx][ny]) {
                    dist[nx][ny] = nextCost;
                    pq.offer(new Node(nx, ny, nextCost));
                }
            }
        }
        return dist[N - 1][N - 1];
    }

    private static class Node {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;

        }
    }
}
