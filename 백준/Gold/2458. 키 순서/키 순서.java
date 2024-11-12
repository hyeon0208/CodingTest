import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static List<Integer>[] bigger;    // 자신보다 큰 학생
    private static List<Integer>[] smaller;   // 자신보다 작은 학생
    private static boolean[] visited;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bigger = new ArrayList[N + 1];
        smaller = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            bigger[i] = new ArrayList<>();
            smaller[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bigger[a].add(b);     // a보다 큰 학생
            smaller[b].add(a);    // b보다 작은 학생
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            int count = dfs(i, bigger) + dfs(i, smaller);

            if (count == N - 1) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static int dfs(int current, List<Integer>[] graph) {
        visited[current] = true;
        int count = 0;

        for (int next : graph[current]) {
            if (!visited[next]) {
                count += dfs(next, graph) + 1;
            }
        }

        return count;
    }
}
