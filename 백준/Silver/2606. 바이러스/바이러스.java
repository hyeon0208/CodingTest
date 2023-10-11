import java.io.*;
import java.util.*;
public class Main {
    static int map[][];
    static boolean visited[];
    static int N;
    static int M;
    static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        map = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = map[b][a] = 1;
        }

        dfs(1);
        System.out.println(result - 1);
    }

    public static void dfs(int start) {
        if (visited[start]) {
            return;
        }
        visited[start] = true;
        result++;
        
        for (int i = 1; i < N + 1; i++) {
            if (map[start][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }
}

