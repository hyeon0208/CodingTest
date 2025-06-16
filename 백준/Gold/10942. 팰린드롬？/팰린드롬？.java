import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/10942

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N][N];

        // 길이가 1인 경우
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        // 길이가 2인 경우
        for (int i = 0; i < N - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        // 길이가 3 이상인 경우
        for (int len = 3; len <= N; len++) {
            for (int i = 0; i < N - len + 1; i++) {
                int j = i + len - 1;
                if (nums[i] == nums[j] && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dp[S][E] ? 1 : 0).append('\n');
        }

        System.out.print(sb);
    }
}
