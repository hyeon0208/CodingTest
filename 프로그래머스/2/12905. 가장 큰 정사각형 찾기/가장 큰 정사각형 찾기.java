class Solution {
    public int solution(int[][] board) {        
        int m = board.length;
        int n = board[0].length;
        int[][] dp = new int[m][n];
        int maxSize = 0;
        
        for (int i = 0; i < m; i++) {
            dp[i][0] = board[i][0];
            maxSize = Math.max(maxSize, dp[i][0]);
        }
        
        for (int j = 0; j < n; j++) {
            dp[0][j] = board[0][j];
            maxSize = Math.max(maxSize, dp[0][j]);
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (board[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }
        
        return maxSize * maxSize;
    }
}