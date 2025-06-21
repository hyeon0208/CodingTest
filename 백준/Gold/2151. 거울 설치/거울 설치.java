import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main { // https://www.acmicpc.net/problem/2151

    private static final char DOOR = '#';
    private static final char BLANK = '.';
    private static final char MIRROR = '!';
    private static final char WALL = '*';
    private static final int[] DX = {0, 0, 1, -1};
    private static final int[] DY = {1, -1, 0, 0};

    private static char[][] maps;
    private static boolean[][][] visited;
    private static List<Position> doors;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        maps = new char[n][n];
        visited = new boolean[n][n][4];
        doors = new ArrayList<>(2);

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == DOOR) {
                    doors.add(new Position(i, j, 0, 0));
                }
                maps[i][j] = line.charAt(j);
            }
        }

        Position start = doors.get(0);
        Position end = doors.get(1);
        System.out.println(dijkstra(start, end));
    }

    private static int dijkstra(Position start, Position end) {
        PriorityQueue<Position> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.mirrors));

        // 시작점에서 갈 수 있는 방향들만 큐에 추가
        for (int dir = 0; dir < 4; dir++) {
            int nx = start.x + DX[dir];
            int ny = start.y + DY[dir];

            if (isValid(nx, ny) && maps[nx][ny] != WALL) {
                pq.offer(new Position(nx, ny, dir, 0));
            }
        }

        // 시작점은 모든 방향에서 방문 처리
        for (int dir = 0; dir < 4; dir++) {
            visited[start.x][start.y][dir] = true;
        }

        while (!pq.isEmpty()) {
            Position cur = pq.poll();

            // 이미 방문한 곳이면 스킵
            if (visited[cur.x][cur.y][cur.dir]) {
                continue;
            }

            // 도착 확인
            if (cur.x == end.x && cur.y == end.y) {
                return cur.mirrors;
            }

            visited[cur.x][cur.y][cur.dir] = true;

            // 거울 설치 가능한 위치에서 방향 전환
            if (maps[cur.x][cur.y] == MIRROR) {
                if (cur.dir == 0 || cur.dir == 1) {  // 수직 방향에서 오면 수평으로만 갈 수 있음
                    for (int newDir = 2; newDir < 4; newDir++) {
                        int nx = cur.x + DX[newDir];
                        int ny = cur.y + DY[newDir];

                        if (isValid(nx, ny) && !visited[nx][ny][newDir] && maps[nx][ny] != WALL) {
                            pq.offer(new Position(nx, ny, newDir, cur.mirrors + 1));
                        }
                    }
                } else { // 수평 방향에서 오면 수직으로만 갈 수 있음
                    for (int newDir = 0; newDir < 2; newDir++) {
                        int nx = cur.x + DX[newDir];
                        int ny = cur.y + DY[newDir];

                        if (isValid(nx, ny) && !visited[nx][ny][newDir] && maps[nx][ny] != WALL) {
                            pq.offer(new Position(nx, ny, newDir, cur.mirrors + 1));
                        }
                    }
                }
            }

            // 직진
            int nx = cur.x + DX[cur.dir];
            int ny = cur.y + DY[cur.dir];
            if (isValid(nx, ny) && !visited[nx][ny][cur.dir] && maps[nx][ny] != WALL) {
                pq.offer(new Position(nx, ny, cur.dir, cur.mirrors));
            }
        }

        return -1;
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private static class Position {
        int x, y, dir, mirrors;

        public Position(int x, int y, int dir, int mirrors) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirrors = mirrors;
        }
    }
}
