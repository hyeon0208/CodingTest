import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    
    public static int solution(int n, int[][] edge) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, -1);

        bfs(graph, distances);

        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            maxDistance = Math.max(maxDistance, distances[i]);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == maxDistance) {
                count++;
            }
        }

        return count;
    }

    private static void bfs(List<List<Integer>> graph, int[] distances) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        distances[1] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int nextNode : graph.get(current)) {
                if (distances[nextNode] == -1) {
                    queue.offer(nextNode);
                    distances[nextNode] = distances[current] + 1;
                }
            }
        }
    }
}