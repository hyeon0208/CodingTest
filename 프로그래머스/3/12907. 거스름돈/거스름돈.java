import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        final int MOD = 1_000_000_007;
        
        int[] dp = new int[n + 1];
        dp[0] = 1;  // 0원을 만드는 방법: 1가지 (아무것도 안 함)
        
        for (int m : money) {
            for (int i = m; i <= n; i++) {
                dp[i] += dp[i - m];
            }
        }
        
        return dp[n] % MOD;
    }
}