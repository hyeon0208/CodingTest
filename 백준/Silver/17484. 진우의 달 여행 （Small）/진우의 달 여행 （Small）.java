import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static int[][] maps;
    private static int result = Integer.MAX_VALUE;

    private static final int[][] DIRECTION = {{1, 0}, {1, 1}, {1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        Queue<Ship> startLine = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    startLine.add(new Ship(i, j, -1, maps[i][j]));
                }
            }
        }
        bfs(startLine);

        System.out.println(result);
    }

    private static void bfs(Queue<Ship> queue) {

        while (!queue.isEmpty()) {
            Ship ship = queue.poll();

            for (int i = 0; i < DIRECTION.length; i++) {
                if (ship.prevDirectionIndex == i) {
                    continue;
                }

                int nextX = ship.x + DIRECTION[i][0];
                int nextY = ship.y + DIRECTION[i][1];

                if (nextX == N - 1 && nextY >= 0 && nextY < M) {
                    result = Math.min(result, ship.score + maps[nextX][nextY]);
                    continue;
                }

                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
                    queue.add(new Ship(nextX, nextY, i, ship.score + maps[nextX][nextY]));
                }
            }
        }
    }

    private static class Ship {
        int x;
        int y;
        int prevDirectionIndex;
        int score;

        public Ship(int x, int y, int prevDirectionIndex, int score) {
            this.x = x;
            this.y = y;
            this.prevDirectionIndex = prevDirectionIndex;
            this.score = score;
        }

    }
}
