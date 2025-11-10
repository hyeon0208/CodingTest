import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N + 1][N + 1];
        
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;  // a에서 b로 가는 경로
        }
        
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                    }
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (graph[j][i] == 1) {
                    count++;
                }
            }
            if (count == N - 1) {
                System.out.println(i);
                return;
            }
        }
        
        System.out.println(-1);
    }
}