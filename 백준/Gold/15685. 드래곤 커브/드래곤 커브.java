import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] grid = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 시작점 x좌표
            int y = Integer.parseInt(st.nextToken()); // 시작점 y좌표
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            int g = Integer.parseInt(st.nextToken()); // 세대

            drawDragonCurve(x, y, d, g);
        }

        int count = countSquares();
        System.out.println(count);
    }

    static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d); // 0세대 방향 추가

        // 세대별 방향 리스트 생성
        for (int i = 0; i < g; i++) {
            int size = directions.size();
            for (int j = size - 1; j >= 0; j--) {
                int newDir = (directions.get(j) + 1) % 4; // 90도 반시계 회전
                directions.add(newDir);
            }
        }

        grid[x][y] = true;
        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            grid[x][y] = true;
        }
    }

    static int countSquares() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (grid[i][j] && grid[i+1][j] && grid[i][j+1] && grid[i+1][j+1]) {
                    count++;
                }
            }
        }
        return count;
    }
}
