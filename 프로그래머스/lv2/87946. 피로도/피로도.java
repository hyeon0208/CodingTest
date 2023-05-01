class Solution {
    private static int answer;
    private static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        visited = new boolean[dungeons.length];
        dfs(dungeons, 0, k);

        return answer;
    }

    public static void dfs(int[][] dungeons, int depth, int k) {
        if (depth > dungeons.length) {
            return;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(dungeons, depth + 1, k - dungeons[i][1]);
                visited[i] = false;
            }
        }
        answer = Math.max(answer, depth);
    }
}