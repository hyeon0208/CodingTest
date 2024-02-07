import java.io.*;

public class Main {
    private static int[] numbers;
    private static boolean[] visited;
    private static int N;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        visited = new boolean[N];
        dfs(0);
        System.out.println(sb);
    }

    private static void dfs(int depth) {
        if (depth == N) {
            for (int i = 0; i < N; i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                numbers[depth] = i + 1;
                visited[i] = true;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}