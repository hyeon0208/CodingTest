import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static List<Integer> results;
//    private static boolean[] visited;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        numbers = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        results = new ArrayList<>();
//        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, i, visited);
//            visited[i] = false;
        }

        Collections.sort(results);
        System.out.println(results.size());
        results.stream().forEach(System.out::println);
    }


    public static void dfs(int start, int target, boolean[] visited) {
        if (numbers[start] == target) {
            results.add(target);
        }
        if (!visited[numbers[start]]) {
            visited[numbers[start]] = true;
            dfs(numbers[start], target, visited);
            visited[numbers[start]] = false;
        }
    }
}
