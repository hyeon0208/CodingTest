import java.io.*;
import java.util.*;

public class Main {
    static int[] result;
    static List<List<Integer>> edges;
    static int idx = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        result = new int[n + 1];
        visited = new boolean[n + 1];
        
        while (m != 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges.get(u).add(v);
            edges.get(v).add(u);
            m--;
        }
        
        for (int i = 1; i <= n; i++) {
            Collections.sort(edges.get(i));
        }
        
        visited[r] = true;
        dfs(r);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int cur) {
        result[cur] = ++idx;
        for (int next : edges.get(cur)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next);
            }
        }
    }
}