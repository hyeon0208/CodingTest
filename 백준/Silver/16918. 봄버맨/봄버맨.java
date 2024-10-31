import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int R;
    private static int C;
    private static int N;
    private static char[][] maps;
    private static int[][] bombTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        maps = new char[R][C];
        bombTime = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                maps[i][j] = line.charAt(j);
                if (maps[i][j] == 'O') {
                    bombTime[i][j] = 3;
                }
            }
        }

        int time = 2;
        while (time <= N) {
            if (time % 2 == 0) {
                installBombs(time);
            } else {
                explodeBombs(time);
            }
            time++;
        }

        printResult();
    }

    private static void installBombs(int time) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maps[i][j] == '.') {
                    maps[i][j] = 'O';
                    bombTime[i][j] = time + 3;
                }
            }
        }
    }

    private static void explodeBombs(int time) {
        boolean[][] willExplode = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maps[i][j] == 'O' && bombTime[i][j] == time) {
                    willExplode[i][j] = true;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + DX[k];
                        int ny = j + DY[k];
                        if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                            willExplode[nx][ny] = true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (willExplode[i][j]) {
                    maps[i][j] = '.';
                    bombTime[i][j] = 0;
                }
            }
        }
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(maps[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
