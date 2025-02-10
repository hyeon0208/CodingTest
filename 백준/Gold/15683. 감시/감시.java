import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final int BLANK = 0;
    private static final int CCTV1 = 1;
    private static final int CCTV2 = 2;
    private static final int CCTV3 = 3;
    private static final int CCTV4 = 4;
    private static final int CCTV5 = 5;
    private static final int WALL = 6;
    private static final int[] DX = {-1, 0, 1, 0};
    private static final int[] DY = {0, 1, 0, -1};

    private static int N;
    private static int M;
    private static int[][] maps;
    private static List<CCTV> cctvs;
    private static int blindSpotCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maps = new int[N][M];
        cctvs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                maps[i][j] = num;
                if (num != BLANK && num != WALL) {
                    cctvs.add(new CCTV(i, j, num));
                }
            }
        }

        dfs(0, maps);

        System.out.println(blindSpotCount);
    }

    private static void dfs(int depth, int[][] copyMaps) {
        if (depth == cctvs.size()) {
            blindSpotCount = Math.min(blindSpotCount, countBlindSpot(copyMaps));
            return;
        }

        CCTV cctv = cctvs.get(depth);
        int rotations = cctv.num == CCTV2 ? 2 : 4;
        int[][] newMaps = new int[N][M];

        for (int i = 0; i < rotations; i++) {
            for (int x = 0; x < N; x++) {
                newMaps[x] = copyMaps[x].clone();
            }
            watch(newMaps, cctv, i);
            dfs(depth + 1, newMaps);
        }
    }

    private static void watch(int[][] copyMaps, CCTV cctv, int dir) {
        if (cctv.num == CCTV1) {
            checkLine(copyMaps, cctv.x, cctv.y, dir);
        } else if (cctv.num == CCTV2) {
            checkLine(copyMaps, cctv.x, cctv.y, dir);
            checkLine(copyMaps, cctv.x, cctv.y, dir + 2);
        } else if (cctv.num == CCTV3) {
            checkLine(copyMaps, cctv.x, cctv.y, dir);
            checkLine(copyMaps, cctv.x, cctv.y, (dir + 1) % 4);
        } else if (cctv.num == CCTV4) {
            checkLine(copyMaps, cctv.x, cctv.y, dir);
            checkLine(copyMaps, cctv.x, cctv.y, (dir + 1) % 4);
            checkLine(copyMaps, cctv.x, cctv.y, (dir + 2) % 4);
        } else if (cctv.num == CCTV5) {
            checkLine(copyMaps, cctv.x, cctv.y, 0);
            checkLine(copyMaps, cctv.x, cctv.y, 1);
            checkLine(copyMaps, cctv.x, cctv.y, 2);
            checkLine(copyMaps, cctv.x, cctv.y, 3);
        }
    }

    private static void checkLine(int[][] copyMaps, int x, int y, int dir) {
        int nx = x;
        int ny = y;

        while (true) {
            nx += DX[dir];
            ny += DY[dir];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || copyMaps[nx][ny] == WALL) {
                break;
            }

            if (copyMaps[nx][ny] == BLANK) {
                copyMaps[nx][ny] = -1;
            }
        }
    }

    private static void checkCCTV5(int x, int y) {
        for (int i = 0; i < 4; i++) {
            checkLine(maps, x, y, i);
        }
    }

    private static int countBlindSpot(int[][] copyMaps) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMaps[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private static class CCTV {
        int x;
        int y;
        int num;

        public CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
