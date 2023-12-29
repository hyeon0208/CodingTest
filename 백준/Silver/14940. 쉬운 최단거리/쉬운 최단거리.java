import java.io.*;
import java.util.*;

public class Main {
    private final static int[] dx = {1, 0, -1, 0};
    private final static int[] dy = {0, -1, 0, 1};
    private static int[][] map, result;
    private static int m, n;
    private static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int startX = 0;
        int startY = 0;

        map = new int[n][m];
        result = new int[n][m];
        isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    startX = i;
                    startY = j;
                } else if (map[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = -1;
                }
            }
        }

        bfs(startX, startY);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(result[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        isVisited[x][y] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
                    continue;
                }
                if (map[nextX][nextY] == 0) {
                    continue;
                }
                if (isVisited[nextX][nextY]) {
                    continue;
                }
                queue.add(new Point(nextX, nextY));
                result[nextX][nextY] = result[cur.x][cur.y] + 1;
                isVisited[nextX][nextY] = true;
            }
        }
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}