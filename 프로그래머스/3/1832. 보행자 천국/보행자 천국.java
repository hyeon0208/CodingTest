class Solution {
    
    private static final int DOWN = 0;
    private static final int RIGHT = 1;
    private static final int PASS = 0;
    private static final int BAN_ALL = 1;
    private static final int BAN_LEFT_AND_RIGHT = 2;
    private static final int MOD = 20170805;

    public static int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];
        dp[0][0][DOWN] = 1;
        dp[0][0][RIGHT] = 1;

        for (int x = 1; x < m; x++) {
            if (cityMap[x][0] == BAN_ALL) {
                break;
            }
            dp[x][0][DOWN] = dp[x - 1][0][DOWN];
        }

        for (int y = 1; y < n; y++) {
            if (cityMap[0][y] == BAN_ALL) {
                break;
            }
            dp[0][y][RIGHT] = dp[0][y - 1][RIGHT];
        }

        for (int x = 1; x < m; x++) {
            for (int y = 1; y < n; y++) {
                if (cityMap[x][y] == BAN_ALL) {
                    dp[x][y][DOWN] = 0;
                    dp[x][y][RIGHT] = 0;
                }
                // 위쪽 셀에서 오는 경우
                if (cityMap[x-1][y] == PASS) {
                    dp[x][y][DOWN] = (dp[x-1][y][DOWN] + dp[x-1][y][RIGHT]) % MOD;
                } else if (cityMap[x-1][y] == BAN_LEFT_AND_RIGHT) {
                    dp[x][y][DOWN] = dp[x-1][y][DOWN];
                }
                // 왼쪽 셀에서 오는 경우
                if (cityMap[x][y-1] == PASS) {
                    dp[x][y][RIGHT] = (dp[x][y-1][DOWN] + dp[x][y-1][RIGHT]) % MOD;
                } else if (cityMap[x][y-1] == BAN_LEFT_AND_RIGHT) {
                    dp[x][y][RIGHT] = dp[x][y-1][RIGHT];
                }
            }
        }
        
        return (dp[m - 1][n - 1][DOWN] + dp[m - 1][n - 1][RIGHT]) % MOD;
    }
}