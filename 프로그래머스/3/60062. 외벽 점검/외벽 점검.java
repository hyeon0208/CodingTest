class Solution {
    private static int minCount = Integer.MAX_VALUE;
    
    public static int solution(int n, int[] weak, int[] dist) {
        int len = weak.length;
        int[] weakExtended = new int[len * 2];
        for (int i = 0; i < len; i++) {
            weakExtended[i] = weak[i];
            weakExtended[i + len] = weak[i] + n;
        }

        for (int start = 0; start < len; start++) {
            boolean[] visited = new boolean[dist.length];
            dfs(dist, visited, start, weakExtended, 0, 0, weak.length);
        }

        if (minCount == Integer.MAX_VALUE) {
            return -1;
        }
        return minCount;
    }

    private static void dfs(int[] dist, boolean[] visited, int start, int[] weakExtended, int count, int covered, int len) {
        if (count >= minCount || count > dist.length) {
            return;
        }

        if (covered >= len) {
            minCount = Math.min(minCount, count);
            return;
        }

        int position = weakExtended[start + covered];

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int nextCovered = covered;
                while (nextCovered < len &&
                        weakExtended[start + nextCovered] <= position + dist[i]) {
                    nextCovered++;
                }
                dfs(dist, visited, start, weakExtended, count + 1, nextCovered, len);
                visited[i] = false;
            }
        }
    }
}