import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final char BLANK = '.';
    private static final char CRAZY_ADU = 'R';
    private static final char ZONGSU = 'I';

    private static char[][] maps;
    private static int R;
    private static int C;
    private static Position zongsuPosition;
    private static int[] moveDirections;
    private static int[] DX = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    private static int[] DY = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    private static int zongsuMoveCount = 0;
    private static boolean success = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maps = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                maps[i][j] = line.charAt(j);
                if (maps[i][j] == ZONGSU) {
                    zongsuPosition = new Position(i, j);
                }
            }
        }

        String directions = br.readLine();
        moveDirections = new int[directions.length()];
        for (int i = 0; i < directions.length(); i++) {
            moveDirections[i] = directions.charAt(i) - '0';
        }

        simulate();

        if (!success) {
            System.out.printf("kraj %d", zongsuMoveCount);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(maps[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void simulate() {
        for (int turn = 0; turn < moveDirections.length; turn++) {
            zongsuMoveCount = turn + 1;

            // 1. 종수 이동
            if (!moveZongsu(moveDirections[turn])) {
                success = false;
                return;
            }

            // 2. 미친 아두이노들의 위치 저장
            List<Position> crazyPositions = new ArrayList<>();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (maps[i][j] == CRAZY_ADU) {
                        crazyPositions.add(new Position(i, j));
                        maps[i][j] = BLANK;
                    }
                }
            }

            // 3. 미친 아두이노 이동 및 충돌 처리
            int[][] crazyCount = new int[R][C];
            for (Position crazy : crazyPositions) {
                Position nextPosition = moveCrazyAdu(crazy);
                if (nextPosition.x == zongsuPosition.x && nextPosition.y == zongsuPosition.y) {
                    success = false;
                    return;
                }
                crazyCount[nextPosition.x][nextPosition.y]++;
            }

            // 4. 충돌 처리 후 맵 업데이트
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (crazyCount[i][j] == 1) {
                        maps[i][j] = CRAZY_ADU;
                    }
                }
            }
        }
    }

    private static boolean moveZongsu(int direction) {
        int nextX = zongsuPosition.x + DX[direction];
        int nextY = zongsuPosition.y + DY[direction];

        if (maps[nextX][nextY] == CRAZY_ADU) {
            return false;
        }

        maps[zongsuPosition.x][zongsuPosition.y] = BLANK;
        maps[nextX][nextY] = ZONGSU;
        zongsuPosition = new Position(nextX, nextY);
        return true;
    }

    private static Position moveCrazyAdu(Position current) {
        int minDist = Integer.MAX_VALUE;
        Position nextPosition = current;

        for (int dir = 1; dir <= 9; dir++) {
            int nx = current.x + DX[dir];
            int ny = current.y + DY[dir];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }

            int dist = Math.abs(nx - zongsuPosition.x) + Math.abs(ny - zongsuPosition.y);
            if (dist < minDist) {
                minDist = dist;
                nextPosition = new Position(nx, ny);
            }        }

        return nextPosition;
    }

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
