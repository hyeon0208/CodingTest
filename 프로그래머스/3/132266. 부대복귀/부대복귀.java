import java.util.*;

class Solution {
    
    private static int[] cost;
    private static List<List<Integer>> nodes;
    
    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        nodes = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int origin = road[0];
            int target = road[1];
            nodes.get(origin).add(target);
            nodes.get(target).add(origin);
        }

        cost = new int[n + 1];
        Arrays.fill(cost, -1);
        bfs(destination);

        for (int i = 0; i < sources.length; i++) {
            answer[i] = cost[sources[i]];
        }

        return answer;
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        cost[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (Integer next : nodes.get(cur)) {
                if (cost[next] == -1) {
                    q.offer(next);
                    cost[next] = cost[cur] + 1;
                }
            }
        }
    }
}