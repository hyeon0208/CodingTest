import java.util.Arrays;

class Solution {
    
    private static final int INF = 100_000;
    
    public static int[] solution(int target) {
        int[][] dp = new int[target + 1][2]; // 0: 0으로 만들기 위한 최소 다트 수, 1: 싱글/불 횟수
        for (int i = 1; i <= target; i++) {
            dp[i][0] = INF;
        }

        for (int i = 1; i <= target; i++) {

            // 불을 맞출 경우
            if (i >= 50) {
                int throwDartCount = dp[i - 50][0] + 1; // 현재 점수(i)에서 특정 다트를 던져 score만큼 감소시킨 후 남은 점수(i-score)를 0으로 만들기 위한 최소 다트 수 + 방금 던진 1회
                int throwSingleAndBull = dp[i - 50][1] + 1;

                if (throwDartCount < dp[i][0] || (throwDartCount == dp[i][0] && throwSingleAndBull > dp[i][1])) {
                    dp[i][0] = throwDartCount;
                    dp[i][1] = throwSingleAndBull;
                }
            }

            // 싱글을 맞출 경우
            for (int j = 1; j <= 20; j++) {
                if (i - j >= 0) {
                    int throwDartCount = dp[i - j][0] + 1;
                    int throwSingleAndBull = dp[i - j][1] + 1;

                    if (throwDartCount < dp[i][0] || (throwDartCount == dp[i][0] && throwSingleAndBull > dp[i][1])) {
                        dp[i][0] = throwDartCount;
                        dp[i][1] = throwSingleAndBull;
                    }
                }
            }

            // 더블을 맞출 경우
            for (int j = 2; j <= 40; j += 2) {
                if (i - j >= 0) {
                    int throwDartCount = dp[i - j][0] + 1;
                    int throwSingleAndBull = dp[i - j][1];

                    if (throwDartCount < dp[i][0] || (throwDartCount == dp[i][0] && throwSingleAndBull > dp[i][1])) {
                        dp[i][0] = throwDartCount;
                        dp[i][1] = throwSingleAndBull;
                    }
                }
            }

            // 트리플을 맞출 경우
            for (int j = 3; j <= 60; j += 3) {
                if (i - j >= 0) {
                    int throwDartCount = dp[i - j][0] + 1;
                    int throwSingleAndBull = dp[i - j][1];

                    if (throwDartCount < dp[i][0] || (throwDartCount == dp[i][0] && throwSingleAndBull > dp[i][1])) {
                        dp[i][0] = throwDartCount;
                        dp[i][1] = throwSingleAndBull;
                    }
                }
            }
        }

        return dp[target];
    }
}