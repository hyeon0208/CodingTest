import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int weight, height, result;
    static boolean[][] visited;
    static Queue<Point> fire;
    static Queue<Point> person;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            weight = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());

            map = new char[height][weight];
            visited = new boolean[height][weight];

            fire = new LinkedList<>();
            person = new LinkedList<>();

            for (int i = 0; i < height; i++) {
                String str = br.readLine();
                for (int j = 0; j < weight; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '@') {
                        person.offer(new Point(i, j, 0));
                    } else if (map[i][j] == '*') {
                        fire.offer(new Point(i, j, 0));
                    }
                }
            }

            result = 0;
            bfs();
            if (result == 0) {
                sb.append("IMPOSSIBLE\n");
            } else {
                sb.append(result + "\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static void bfs() {

        while (!person.isEmpty()) {
            int size = fire.size();
            for (int i = 0; i < size; i++) {
                Point firePoint = fire.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = firePoint.x + dx[d];
                    int ny = firePoint.y + dy[d];
                    if (isValidRange(nx, ny) && (map[nx][ny] == '.' || map[nx][ny] == '@')) {
                        map[nx][ny] = '*';
                        fire.offer(new Point(nx, ny, 0));
                    }
                }
            }

            size = person.size();
            for (int i = 0; i < size; i++) {
                Point personPoint = person.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = personPoint.x + dx[d];
                    int ny = personPoint.y + dy[d];
                    if (!isValidRange(nx, ny)) {
                        result = personPoint.time + 1;
                        return;
                    }
                    if (map[nx][ny] == '.') {
                        map[nx][ny] = '@';
                        person.offer(new Point(nx, ny, personPoint.time + 1));
                    }

                }
            }
        }
    }

    private static boolean isValidRange(int nx, int ny) {
        return nx >= 0 && nx < height && ny >= 0 && ny < weight;
    }

    static class Point {
        int x;
        int y;
        int time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
