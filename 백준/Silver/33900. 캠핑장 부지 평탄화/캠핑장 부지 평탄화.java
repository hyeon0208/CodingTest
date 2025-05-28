import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] plan = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                plan[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i <= N - R; i++) {
            for (int j = 0; j <= M - C; j++) {
                int base = map[i][j] - plan[0][0];
                boolean ok = true;
                for (int x = 0; x < R && ok; x++) {
                    for (int y = 0; y < C; y++) {
                        if (map[i + x][j + y] - plan[x][y] != base) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) count++;
            }
        }
        System.out.println(count);
    }
}
