import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] maps = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// dir: 0=위, 1=왼쪽, 2=오른쪽
		int[][][] dp = new int[N][M][3];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(dp[i][j], Integer.MIN_VALUE / 2);
			}
		}

		dp[0][0][1] = maps[0][0]; // 시작점은 왼쪽에서 온 것으로 간주
		for (int j = 1; j < M; j++) {
			dp[0][j][1] = dp[0][j - 1][1] + maps[0][j];
		}

		// 두 번째 행부터
		for (int i = 1; i < N; i++) {
			// 왼쪽에서 오른쪽으로 처리 (dir=0, dir=1)
			for (int j = 0; j < M; j++) {
				// 위에서 내려오는 경우
				dp[i][j][0] = Math.max(dp[i - 1][j][0], Math.max(dp[i - 1][j][1], dp[i - 1][j][2])) + maps[i][j];

				// 왼쪽에서 오는 경우
				if (j > 0) {
					dp[i][j][1] = Math.max(dp[i][j - 1][0], dp[i][j - 1][1]) + maps[i][j];
				}
			}

			// 오른쪽에서 왼쪽으로 처리 (dir=2)
			for (int j = M - 1; j >= 0; j--) {
				// 오른쪽에서 오는 경우
				if (j < M - 1) {
					dp[i][j][2] = Math.max(dp[i][j + 1][0], dp[i][j + 1][2]) + maps[i][j];
				}
			}
		}

		int result = Math.max(dp[N - 1][M - 1][0], Math.max(dp[N - 1][M - 1][1], dp[N - 1][M - 1][2]));
		System.out.println(result);
	}
}
