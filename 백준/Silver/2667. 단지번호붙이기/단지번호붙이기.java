import java.io.*;
import java.util.*;

public class Main {
    static int map[][];
    static boolean visited[][];
    static int N;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static List<Integer> counts = new ArrayList<>(N);
    static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N][N];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!visited[x][y] && map[x][y] == 1) {
                    visited[x][y] = true;
                    counts.add(bfs(x, y));
                    result++;
                }
            }
        }

        System.out.println(result);
        Collections.sort(counts);
        for (Integer count : counts) {
            System.out.println(count);
        }
    }

    public static int bfs(int x, int y) {
        int count = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] curArr = q.poll();
            int curX = curArr[0];
            int curY = curArr[1];

            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + curX;
                int nextY = dy[i] + curY;

                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
                    if (!visited[nextX][nextY] && map[nextX][nextY] == 1) {
                        visited[nextX][nextY] = true;
                        q.add(new int[]{nextX, nextY});
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

