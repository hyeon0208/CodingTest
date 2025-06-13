import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] process = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            process[i][0] = Integer.parseInt(st.nextToken());
            process[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][N];
        for (int len = 2; len <= N; len++) { // 2개의 연속된 행렬 부터 곱하기
            for (int i = 0; i <= N - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) { // i와 j 사이의 모든 분할점 k에 대해 계산
                    int cost = dp[i][k] + dp[k + 1][j] + (process[i][0] * process[k][1] * process[j][1]);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        System.out.println(dp[0][N - 1]);
    }
}
