import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final char MINERAL = 'x';
    private static final char EMPTY = '.';
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    private static int R, C;
    private static char[][] maps;

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
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = R - Integer.parseInt(st.nextToken());
            shoot(i, height);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(maps[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void shoot(int i, int height) {
        if (i % 2 == 0) { // 창영 차례
            for (int c = 0; c < C; c++) {
                if (maps[height][c] == MINERAL) {
                    maps[height][c] = EMPTY;
                    checkCluster();
                    break;
                }
            }
        } else { // 상근 차례
            for (int c = C - 1; c >= 0; c--) {
                if (maps[height][c] == MINERAL) {
                    maps[height][c] = EMPTY;
                    checkCluster();
                    break;
                }
            }
        }
    }

    private static void checkCluster() {
        boolean[][] visited = new boolean[R][C];

        // 바닥에서 부터 연결된 미네랄 체크 (visited = true인 곳은 바닥과 연결된 미네랄들
        for (int c = 0; c < C; c++) {
            if (maps[R - 1][c] == MINERAL && !visited[R - 1][c]) {
                dfs(R - 1, c, visited);
            }
        }

        // 공중에 떠 있는 미네랄 좌표 저장
        List<Position> cluster = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (maps[r][c] == MINERAL && !visited[r][c]) { // 미네랄이면서, 바닥과 연결된 미네랄이 아닌 경우
                    cluster.add(new Position(r, c));
                    maps[r][c] = EMPTY;
                }
            }
        }

        // 클러스가 없으면 종료
        if (cluster.isEmpty()) {
            return;
        }

        // 클러스터 낙하 거리 계산
        int distance = getDistance(cluster);

        // 클러스터 새 위치에 배치
        for (Position pos : cluster) {
            maps[pos.x + distance][pos.y] = MINERAL;
        }
    }

    // 바닥과 연결된 미네랄 찾기
    private static void dfs(int r, int c, boolean[][] visited) {
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];

            if (nr >= 0 && nr < R && nc >= 0 && nc < C && maps[nr][nc] == MINERAL && !visited[nr][nc]) {
                dfs(nr, nc, visited);
            }
        }
    }

    // 낙하 거리 계산
    private static int getDistance(List<Position> cluster) {
        int distance = Integer.MAX_VALUE; // 낙하 가능 거리

        for (Position pos : cluster) {
            int r = pos.x;
            int c = pos.y;
            int height = 0;

            // 현재 위치부터 아래로 내려가며 빈 칸 개수 세기
            for (int i = r + 1; i < R; i++) {
                if (maps[i][c] == EMPTY) {
                    height++;
                } else {
                    break;
                }
            }

            distance = Math.min(distance, height); // 최솟 값이 아래 미네랄과 겹치지 않음
        }

        return distance;
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
