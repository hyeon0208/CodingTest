import java.io.*;
import java.util.*;

public class Main {
    static String[][] map;
    static boolean[][] visited;
    static int N;
    static int M;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new String[N][M];
        visited = new boolean[N][M];
        for (String[] strings : map) {
            Arrays.fill(strings, ".");
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = "#";
        }
        
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == "#") {
                    visited[i][j] = true;
                    results.add(bfs(i, j));
                }
            }
        }

        System.out.println(Collections.max(results));
    }

    static int bfs(int x, int y) {
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] curArr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = curArr[0] + dx[i];
                int nextY = curArr[1] + dy[i];

                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
                    if (!visited[nextX][nextY] && map[nextX][nextY] == "#") {
                        q.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}

