import java.util.*;

class Solution {
    
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static long answer = 0;
    private static long[] arr;
    private static int[] degree;

    public static long solution(int[] a, int[][] edges) {
        long sum = 0;
        for (int i : a) {
            sum += i;
        }
        if (sum != 0) {
            return -1;
        }

        int count = 0;
        for (int i : a) {
            if (i == 0) {
                count++;
            }
        }
        if (count == a.length) {
            return 0;
        }

        graph = new ArrayList<>(a.length);
        degree = new int[a.length];
        visited = new boolean[a.length];
        for (int i = 0; i < a.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            graph.get(x).add(y);
            graph.get(y).add(x);
            degree[x]++;
            degree[y]++;
        }

        arr = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            arr[i] = a[i];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = true;

            for (Integer next : graph.get(cur)) {
                if (!visited[next]) {
                    degree[next]--;
                    arr[next] += arr[cur];
                    answer += Math.abs(arr[cur]);
                    arr[cur] = 0;
                    if (degree[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }
        return answer;
    }
}