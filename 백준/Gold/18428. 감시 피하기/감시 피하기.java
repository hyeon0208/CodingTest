import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final char TEACHER = 'T';
    private static final char STUDENT = 'S';
    private static final char WALL = 'X';
    private static final char HURDLE = 'O';
    private static final int HURDLE_COUNT = 3;
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int N;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String[] parts = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = parts[j].charAt(0);
            }
        }

        System.out.println(dfs(0) ? "YES" : "NO");
    }

    private static boolean dfs(int count) {
        if (count == HURDLE_COUNT) {
            return check();
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == WALL) {
                    map[i][j] = HURDLE;
                    if (dfs(count + 1)) {
                        return true;
                    }
                    map[i][j] = WALL;
                }
            }
        }
        return false;
    }

    private static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == TEACHER) {
                    for (int d = 0; d < 4; d++) {
                        int nx = i;
                        int ny = j;

                        while (true) {
                            nx += DX[d];
                            ny += DY[d];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                                break;
                            }
                            if (map[nx][ny] == HURDLE) {
                                break;
                            }
                            if (map[nx][ny] == STUDENT) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
