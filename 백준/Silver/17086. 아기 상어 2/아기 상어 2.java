import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int BLANK = 0;
    private static final int BABY_SHARK = 1;

    private static int[] DX = {0, 0, 1, -1, 1, 1, -1, -1};
    private static int[] DY = {1, -1, 0, 0, 1, -1, 1, -1};

    private static int N;
    private static int M;
    private static int[][] maps;
    private static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i][j] == BLANK) {
                    result = Math.max(result, bfs(i, j));
                }
            }
        }

        System.out.println(result);
    }

    private static int bfs(int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(x, y, 0));
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Position cur = queue.poll();

            if (maps[cur.x][cur.y] == BABY_SHARK) {
                return cur.distance;
            }

            for (int i = 0; i < 8; i++) {
                int nx = DX[i] + cur.x;
                int ny = DY[i] + cur.y;

                if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                    queue.offer(new Position(nx, ny, cur.distance + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        return 0;
    }

    private static class Position {
        int x;
        int y;
        int distance;

        public Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
