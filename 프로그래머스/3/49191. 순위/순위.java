import java.util.*;

class Solution {
    public static int solution(int n, int[][] results) {
        Map<Integer, List<Integer>> winMap = new HashMap<>();
        Map<Integer, List<Integer>> loseMap = new HashMap<>();

        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            winMap.computeIfAbsent(winner, k -> new ArrayList<>()).add(loser);
            loseMap.computeIfAbsent(loser, k -> new ArrayList<>()).add(winner);
        }

        int answer = 0;
        for (int player = 1; player <= n; player++) {
            int winCount = countReachableNodes(player, winMap);
            int loseCount = countReachableNodes(player, loseMap);
            if (winCount + loseCount == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    private static int countReachableNodes(int start, Map<Integer, List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            List<Integer> neighbors = graph.getOrDefault(current, new ArrayList<>());
            for (int next : neighbors) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        // 자기 자신은 제외
        return visited.size() - 1;
    }
}