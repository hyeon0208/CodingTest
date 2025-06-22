import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main { // https://www.acmicpc.net/problem/16954

    private static final char WALL = '#';
    private static final char BLANK = '.';

    private static final int[] DX = {-1, 0, 1, 0, 0, 1, 1, -1, -1};
    private static final int[] DY = {0, -1, 0, 1, 0, 1, -1, 1, -1};

    private static char[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        maps = new char[8][8];
        for (int i = 0; i < 8; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                maps[i][j] = line.charAt(j);
            }
        }

        // 맨 아래 왼쪽 에서 맨 위 오른쪽으로 갈 수 있으면 1, 아니면 0
        System.out.println(bfs() ? 1 : 0);
    }

    private static boolean bfs() {
        Queue<Position> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[8][8][65]; // 시간은 최대 64초면 충분
        queue.offer(new Position(7, 0, 0));
        visited[7][0][0] = true;

        while (!queue.isEmpty()) {
            Position cur = queue.poll();

            if (cur.time >= 8) { // 벽이 모두 사라진 후에는 자유롭게 이동 가능
                return true;
            }

            if (cur.x == 0 && cur.y == 7) {
                return true;
            }

            for (int i = 0; i < 9; i++) {
                int nx = cur.x + DX[i];
                int ny = cur.y + DY[i];
                int ntime = cur.time + 1;

                if (0 <= nx && nx < 8 && 0 <= ny && ny < 8 && !visited[nx][ny][ntime]) {
                    if (!hasWall(nx, ny, ntime) && !hasWall(nx, ny, cur.time)) { // 현재 시간 그리고 다음 시간에도 해당 위치에 벽이 없어야함.
                        visited[nx][ny][ntime] = true;
                        queue.offer(new Position(nx, ny, ntime));
                    }
                }
            }
        }

        return false;
    }

    private static boolean hasWall(int x, int y, int time) {
        // 벽이 아래로 내려가므로, 시간 time에서 (x,y)에 벽이 있으려면 원래 (x-time, y) 위치에 벽이 있어야 함
        int originalX = x - time;

        // 원래 벽 위치가 유효한 범위 내에 있고, 그 위치에 벽이 있었다면
        if (0 <= originalX && originalX < 8) {
            return maps[originalX][y] == WALL;
        }
        return false;
    }

    private static class Position {
        int x, y, time;

        public Position(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
