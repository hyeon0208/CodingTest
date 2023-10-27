import java.io.*;
import java.util.*;
import org.w3c.dom.Node;

public class Main {
    private static int dx[] = {0, 0, -1, 1, -1, 1, -1, 1}; // 상, 하, 좌, 우, 상좌, 상우, 하좌, 하우
    private static int dy[] = {-1, 1, 0, 0, 1, 1, -1, -1}; // 상, 하, 좌, 우, 상좌, 상우, 하좌, 하우
    private static int map[][];
    private static boolean visited[][];
    private static int w;
    private static int h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line;
        while (!(line = br.readLine()).equals("0 0")) {
            StringTokenizer st = new StringTokenizer(line);
            w = Integer.parseInt(st.nextToken()); // 가로 (열)
            h = Integer.parseInt(st.nextToken()); // 세로 (행)
            map = new int[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        visited[i][j] = true;
                        bfs(i, j);
                        count++;
                    }
                }
            }
            sb.append(count).append('\n');
        }
        System.out.println(sb);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] curArr = q.poll();
            int curX = curArr[0];
            int curY = curArr[1];
            for (int i = 0; i < 8; i++) {
                int nextX = dx[i] + curX;
                int nextY = dy[i] + curY;

                if (nextX >= 0 && nextX < h && nextY >= 0 && nextY < w) {
                    if (!visited[nextX][nextY] && map[nextX][nextY] == 1) {
                        visited[nextX][nextY] = true;
                        q.add(new int[]{nextX, nextY});
                    }
                }
            }
        }
    }
}

