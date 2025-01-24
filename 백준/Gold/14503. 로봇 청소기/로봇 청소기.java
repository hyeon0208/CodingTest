import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int BLANK = 0;
    private static final int WALL = 1;

    private static int[] DX = {-1, 0, 1, 0};
    private static int[] DY = {0, 1, 0, -1};

    private static int N;
    private static int M;
    private static int r;
    private static int c;
    private static int d;
    private static int[][] maps;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Robot robot = new Robot(r, c, d, 0);
        System.out.println(robot.clean());
    }

    private static class Robot {
        int x;
        int y;
        int direction;
        int cleanCount;

        public Robot(int x, int y, int direction, int cleanCount) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cleanCount = cleanCount;
        }

        public int clean() {
            while (true) {
                if (maps[x][y] == BLANK && !visited[x][y]) {
                    visited[x][y] = true;
                    cleanCount++;
                }

                if (!check4WayCleaningPossible()) {
                    int backX = DX[(direction + 2) % 4] + x;
                    int backY = DY[(direction + 2) % 4] + y;
                    if(backX >= 0 && backX < N && backY >= 0 && backY < M && maps[backX][backY] == BLANK) {
                        x = backX;
                        y = backY;
                    } else {
                        break;
                    }
                } else {
                    direction = (direction + 3) % 4;
                    int nx = DX[direction] + x;
                    int ny = DY[direction] + y;

                    if(nx >= 0 && nx < N && ny >= 0 && ny < M && maps[nx][ny] == BLANK && !visited[nx][ny]) {
                        x = nx;
                        y = ny;
                    }
                }
            }
            return cleanCount;
        }

        private boolean check4WayCleaningPossible() {
            for (int i = 0; i < 4; i++) {
                int nx = x + DX[i];
                int ny = y + DY[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && maps[nx][ny] == BLANK && !visited[nx][ny]) {
                    return true;
                }
            }
            return false;
        }
    }
}
