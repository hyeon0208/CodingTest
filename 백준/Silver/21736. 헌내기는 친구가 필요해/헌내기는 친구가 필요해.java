import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static char[][] campus;
    private static boolean[][] visited;
    private static int startX;
    private static int startY;
    private static int friendCount = 0;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        campus = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String command = br.readLine();
            for (int j = 0; j < M; j++) {
                campus[i][j] = command.charAt(j);
                if (campus[i][j] == 'I') {
                    startX = i;
                    startY = j;
                }
            }
        }
        bfs();
        if (friendCount == 0) {
            System.out.println("TT");
        }
        if (friendCount > 0) {
            System.out.println(friendCount);
        }
    }

    public static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startX, startY));
        visited[startX][startY] = true;
        while (!q.isEmpty()) {
            Point curPoint = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curPoint.x + dx[i];
                int ny = curPoint.y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (!visited[nx][ny] && campus[nx][ny] != 'X') {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                        if (campus[nx][ny] == 'P') {
                            friendCount++;
                        }
                    }
                }
            }
        }
    }

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}