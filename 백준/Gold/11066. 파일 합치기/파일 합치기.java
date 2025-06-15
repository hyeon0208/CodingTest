import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/11066

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());

            int[] files = new int[K];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
            }

            int result = solve(files);
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }

    public static int solve(int[] files) {
        int n = files.length;

        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + files[i];
        }

        // dp[i][j] = i번째부터 j번째 파일까지 합치는 최소 비용
        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {        // 합칠 파일의 개수 (길이)
            for (int i = 0; i <= n - len; i++) {    // 시작 인덱스
                int j = i + len - 1;                // 끝 인덱스
                dp[i][j] = Integer.MAX_VALUE;

                // k 지점에서 분할하는 모든 경우를 시도
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + getSum(prefixSum, i, j); // 왼쪽 부분 비용 + 오른쪽 부분 비용 + 합치는 비용
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n - 1];
    }

    private static int getSum(int[] prefixSum, int start, int end) {
        return prefixSum[end + 1] - prefixSum[start];
    }
}
