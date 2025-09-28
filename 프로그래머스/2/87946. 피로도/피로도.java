class Solution {
    private static int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(k, 0, visited, dungeons);
        return answer;
    }
    
    private void dfs(int k, int depth, boolean[] visited, int[][] dungeons) {
        if (depth > visited.length) {
            return;
        }
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                if (k >= dungeons[i][0]) {
                    visited[i] = true;
                    dfs(k - dungeons[i][1], depth + 1, visited, dungeons);
                    visited[i] = false;
                }
            }
        }
        answer = Math.max(answer, depth);
    }
}