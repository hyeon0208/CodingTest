class Solution {
    public static int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        int[][] dp = new int[n][n]; // i번째 행렬부터 j번째 행렬까지 곱하는 데 필요한 최소 연산 횟수
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int i = 0; i < n; i++) { // 구간 길이
            for (int start = 0; start < n - i; start++) { // 시작점
                int end = i + start; // 끝점
                for (int k = start; k < end; k++) { // 분할 지점
                    dp[start][end] = Math.min(dp[start][end], dp[start][k] + dp[k + 1][end]
                            + matrix_sizes[start][0] * matrix_sizes[k][1] * matrix_sizes[end][1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}