import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/17404

    private static final int INF = 1000;

    private static int n;
    private static int result = Integer.MAX_VALUE;
    private static int[][] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        house = new int[n][3]; // 빨강, 초록, 파랑
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            house[i][0] = Integer.parseInt(st.nextToken()); // 빨간색
            house[i][1] = Integer.parseInt(st.nextToken()); // 초록색
            house[i][2] = Integer.parseInt(st.nextToken()); // 파란색
        }

        // 첫 번째 집의 색깔을 각각 고정하여 계산
        for (int firstColor = 0; firstColor < 3; firstColor++) {
            int[][] dp = new int[n][3]; // i번째 집을 j색으로 칠했을 때의 최소 비용

            // 첫 번째 집 초기화
            for (int color = 0; color < 3; color++) {
                if (color == firstColor) {
                    dp[0][color] = house[0][color];
                } else {
                    dp[0][color] = INF; // 불가능한 경우 (오버 플로우 방지)
                }
            }

            // 인접한 이전 집 dp 처리
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + house[i][0]; // 이전 집이 초록/파랑이면 빨강 가능
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + house[i][1]; // 이전 집이 빨강/파랑이면 초록 가능
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + house[i][2]; // 이전 집이 빨강/초록이면 파랑 가능
            }

            // 마지막 집에서 첫 번째 집과 다른 색깔만 선택
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor != firstColor) {
                    result = Math.min(result, dp[n - 1][lastColor]);
                }
            }
        }

        System.out.println(result);
    }
}
