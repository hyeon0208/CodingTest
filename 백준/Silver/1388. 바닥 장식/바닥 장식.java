import java.io.*;
import java.util.*;

public class Main {
    private static char[][] map;
    private static boolean[][] visited;
    private static int N;
    private static int M;
    private static int result;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    if (map[i][j] == '-') {
                        dfsX(i, j);
                    }
                    if (map[i][j] == '|') {
                        dfsY(i, j);
                    }
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    private static void dfsX(int x, int y) {
        if (map[x][y] != '-') {
            return;
        }
        visited[x][y] = true;
        if (y + 1 >= M) {
            return;
        }
        dfsX(x, y + 1);
    }

    private static void dfsY(int x, int y) {
        if (map[x][y] != '|') {
            return;
        }
        visited[x][y] = true;
        if (x + 1 >= N) {
            return;
        }
        dfsY(x + 1, y);
    }
}