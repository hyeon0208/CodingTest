import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] result;
    static int max = Integer.MIN_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[N];
        result = new int[N];
        dfs(0);
        System.out.println(max);
    }

    static void dfs(int depth) {
        if (depth == N) {
            int sum = 0;
            for (int i = 0; i < N - 1; i++) {
                sum += Math.abs(result[i] - result[i + 1]);
            }
            max = Math.max(sum, max);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}

