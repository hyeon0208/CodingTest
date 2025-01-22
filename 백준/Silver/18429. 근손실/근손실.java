import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int TARGET_WIGHT = 500;

    private static int N;
    private static int K;
    private static int[] kits;
    private static boolean[] visited;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        kits = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        count = 0;
        dfs(0, TARGET_WIGHT);

        System.out.println(count);
    }

    private static void dfs(int depth, int sum) {
        if (depth == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                if (sum + kits[i] - K < TARGET_WIGHT) {
                    continue;
                }

                visited[i] = true;
                dfs(depth + 1, sum + kits[i] - K);
                visited[i] = false;
            }
        }
    }
}
