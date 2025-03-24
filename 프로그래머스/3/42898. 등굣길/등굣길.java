class Solution {
    
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1000000007;

        int[][] dp = new int[n][m];
        for (int i = 0; i < puddles.length; i++) {
            dp[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
        }

        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 && j == 0) || dp[i][j] == -1) {
                    continue;
                }

                // 위쪽에서 오는 경로 확인 (i > 0인 경우만)
                int fromUp = 0;
                if (i > 0) {
                    fromUp = (dp[i - 1][j] == -1) ? 0 : dp[i - 1][j];
                }

                // 왼쪽에서 오는 경로 확인 (j > 0인 경우만)
                int fromLeft = 0;
                if (j > 0) {
                    fromLeft = (dp[i][j - 1] == -1) ? 0 : dp[i][j - 1];
                }

                dp[i][j] = (fromUp + fromLeft) % MOD;
            }
        }

        return dp[n - 1][m - 1];
    }
}