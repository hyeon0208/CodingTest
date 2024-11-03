import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] cities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;

        cities = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            cities[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        for (int i = 1; i < M; i++) {
            int next = Integer.parseInt(st.nextToken());
            if (find(start) != find(next)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    private static int find(int x) {
        if (cities[x] == x) {
            return x;
        }
        return cities[x] = find(cities[x]);
    }

    public static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB) {
            return;
        }

        if (parentA < parentB) {
            cities[parentB] = parentA;
        } else {
            cities[parentA] = parentB;
        }
    }
}
