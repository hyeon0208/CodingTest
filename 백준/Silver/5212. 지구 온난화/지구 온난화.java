import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static final char LAND = 'X';
    private static final char SEA = '.';

    private static int R, C;
    private static char[][] maps;
    private static char[][] after50Years;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maps = new char[R][C];
        after50Years = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                maps[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            Arrays.fill(after50Years[i], SEA);
        }

        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (maps[x][y] == LAND && !visited[x][y]) {
                    int seaCount = countSea(x, y);
                    if (seaCount < 3) {
                        after50Years[x][y] = LAND;
                    }
                }
            }
        }

        int minR = R, maxR = -1, minC = C, maxC = -1;
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (after50Years[x][y] == LAND) {
                    minR = Math.min(minR, x);
                    maxR = Math.max(maxR, x);
                    minC = Math.min(minC, y);
                    maxC = Math.max(maxC, y);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int x = minR; x <= maxR; x++) {
            for (int y = minC; y <= maxC; y++) {
                sb.append(after50Years[x][y]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int countSea(int x, int y) {
        int seaCount = 0;
        visited[x][y] = true;

        for (int k = 0; k < 4; k++) {
            int nx = x + DX[k];
            int ny = y + DY[k];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C || maps[nx][ny] == SEA) {
                seaCount++;
            }
        }
        return seaCount;
    }
}
