import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import org.w3c.dom.Node;

public class Main {

    private static int W, H;
    private static char[][] map;
    private static int[][][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static List<int[]> cPositions = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        visited = new int[H][W][4];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'C') {
                    cPositions.add(new int[]{i, j});
                }
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.mirrors));

        int startX = cPositions.get(0)[0];
        int startY = cPositions.get(0)[1];
        int endX = cPositions.get(1)[0];
        int endY = cPositions.get(1)[1];

        for (int i = 0; i < 4; i++) {
            visited[startX][startY][i] = 0;
            pq.offer(new Node(startX, startY, i, 0));
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.x == endX && current.y == endY) {
                return current.mirrors;
            }

            if (visited[current.x][current.y][current.dir] < current.mirrors) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                if (Math.abs(current.dir - i) == 2) {
                    continue;
                }

                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
                    continue;
                }

                if (map[nx][ny] == '*') {
                    continue;
                }

                int newMirrors = current.mirrors;
                if (current.dir != i) {
                    newMirrors++;
                }

                if (visited[nx][ny][i] > newMirrors) {
                    visited[nx][ny][i] = newMirrors;
                    pq.offer(new Node(nx, ny, i, newMirrors));
                }
            }
        }

        return -1;
    }

    private static class Node {
        int x, y, dir, mirrors;

        Node(int x, int y, int dir, int mirrors) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirrors = mirrors;
        }
    }
}