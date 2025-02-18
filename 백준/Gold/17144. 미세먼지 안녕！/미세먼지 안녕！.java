import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final int[] DX = {0, -1, 0, 1};
    private static final int[] DY = {1, 0, -1, 0};
    private static final int AIR_PURIFIER = -1;
    private static final int BLANK = 0;

    private static int R;
    private static int C;
    private static int T;
    private static int[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        maps = new int[R][C];
        List<Position> airPurifierPositions = new ArrayList<>(2);
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int i1 = Integer.parseInt(st.nextToken());
                if (i1 == AIR_PURIFIER) {
                    airPurifierPositions.add(new Position(i, j, 0));
                }
                maps[i][j] = i1;
            }
        }

        int[][] copyMaps = new int[R][C];
        while (T-- > 0) {
            for (int i = 0; i < R; i++) {
                copyMaps[i] = maps[i].clone();
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    spread(i, j, copyMaps);
                }
            }
            airClean(airPurifierPositions, copyMaps);

            for (int i = 0; i < R; i++) {
                maps[i] = copyMaps[i].clone();
            }
        }

        System.out.println(countDust());
    }

    // 다음 방향에 공기 청정기가 있거나 원래 미세먼지가 없었던 곳에서는 확산이 일어나지 않는다.
    // 확산 되는 양은 원래 갖고 있던 해당 위치의 미세먼지 수 / 5이다.
    // 확산 후 남는 미세 먼지 양은 (원래 갖고 있던 해당 위치의 미세먼지 수 / 5) * 확산된 방향 수를 뺀다.
    private static void spread(int x, int y, int[][] copyMaps) {
        int count = 0;
        int spreadAmount = maps[x][y] / 5;
        if (spreadAmount == 0 || maps[x][y] == BLANK) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];

            if (0 <= nx && nx < R && 0 <= ny && ny < C && maps[nx][ny] != AIR_PURIFIER) {
                copyMaps[nx][ny] += spreadAmount;
                count++;
            }
        }
        copyMaps[x][y] -= spreadAmount * count;
    }

    private static void airClean(List<Position> positions, int[][] copyMaps) {
        // 위쪽 공기청정기 (반시계 방향)
        int top = positions.get(0).x;
        for (int x = top - 1; x > 0; x--) {
            copyMaps[x][0] = copyMaps[x - 1][0];
        }

        for (int y = 0; y < C - 1; y++) {
            copyMaps[0][y] = copyMaps[0][y + 1];
        }

        for (int x = 0; x < top; x++) {
            copyMaps[x][C - 1] = copyMaps[x + 1][C - 1];
        }

        for (int y = C - 1; y > 1; y--) {
            copyMaps[top][y] = copyMaps[top][y - 1];
        }
        copyMaps[top][1] = 0;

        // 아래쪽 공기청정기 (시계 방향)
        int bottom = positions.get(1).x;
        for (int x = bottom + 1; x < R - 1; x++) {
            copyMaps[x][0] = copyMaps[x + 1][0];
        }

        for (int y = 0; y < C - 1; y++) {
            copyMaps[R - 1][y] = copyMaps[R - 1][y + 1];
        }

        for (int x = R - 1; x > bottom; x--) {
            copyMaps[x][C - 1] = copyMaps[x - 1][C - 1];
        }

        for (int y = C - 1; y > 1; y--) {
            copyMaps[bottom][y] = copyMaps[bottom][y - 1];
        }
        copyMaps[bottom][1] = 0;
    }

    private static int countDust() {
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maps[i][j] != AIR_PURIFIER) {
                    count += maps[i][j];
                }
            }
        }
        return count;
    }

    private static class Position {
        int x;
        int y;
        int prevValue;

        public Position(int x, int y, int prevValue) {
            this.x = x;
            this.y = y;
            this.prevValue = prevValue;
        }
    }
}
