import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final char EMPTY = '.';
    private static final char FENCE = '#';
    private static final char SHEEP = 'o';
    private static final char WOLF = 'v';
    private static int[] DX = {-1, 1, 0, 0};
    private static int[] DY = {0, 0, -1, 1};
    private static int R;
    private static int C;
    private static char[][] fields;
    private static boolean[][] visited;
    private static int sheepCnt, wolfCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        fields = new char[R][C];
        visited = new boolean[R][C];
        for (int x = 0; x < R; x++) {
            String line = br.readLine();
            for (int y = 0; y < C; y++) {
                fields[x][y] = line.charAt(y);
            }
        }

        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (!visited[x][y] && fields[x][y] != FENCE) {
                    bfs(x, y);
                }
            }
        }


        System.out.println(sheepCnt + " " + wolfCnt);
    }

    private static void bfs(int x, int y) {
        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(x, y));
        visited[x][y] = true;

        int tempSheepCnt = 0;
        int tempWolfCnt = 0;

        if (fields[x][y] == SHEEP) {
            tempSheepCnt++;
        }
        if (fields[x][y] == WOLF) {
            tempWolfCnt++;
        }

        while (!queue.isEmpty()) {
            Location cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + DX[i];
                int nextY = cur.y + DY[i];

                if (0 <= nextX && nextX < R && 0 <= nextY && nextY < C && !visited[nextX][nextY]) {
                    if (fields[nextX][nextY] == FENCE) {
                        visited[nextX][nextY] = true;
                        continue;
                    }

                    if (fields[nextX][nextY] == SHEEP) {
                        tempSheepCnt++;
                    }
                    if (fields[nextX][nextY] == WOLF) {
                        tempWolfCnt++;
                    }
                    visited[nextX][nextY] = true;
                    queue.add(new Location(nextX, nextY));
                }
            }
        }
        if (tempSheepCnt > tempWolfCnt) {
            sheepCnt += tempSheepCnt;
        } else {
            wolfCnt += tempWolfCnt;
        }
    }

    private static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
