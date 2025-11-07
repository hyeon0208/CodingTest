import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int T;
	private static int A;
	private static int B;
	private static boolean[][] visited;
	private static int maxFullness;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken()); // 최대 포만도
		A = Integer.parseInt(st.nextToken()); // 오렌지 먹을 시 증가량
		B = Integer.parseInt(st.nextToken()); // 레몬 먹을 시 증가량

		visited = new boolean[T + 1][2];
		maxFullness = 0;

		dfs(0, 0); // 초기 포만도 0, 물 안 마신 상태

		System.out.println(maxFullness);
	}

	private static void dfs(int currentFullness, int drankWater) {
		if (currentFullness > T) {
			return;
		}

		if (visited[currentFullness][drankWater]) {
			return;
		}

		visited[currentFullness][drankWater] = true;

		maxFullness = Math.max(maxFullness, currentFullness);

		// 1. 오렌지 먹기
		dfs(currentFullness + A, drankWater);

		// 2. 레몬 먹기
		dfs(currentFullness + B, drankWater);

		// 3. 물 마시기 (아직 안 마신 경우에만)
		if (drankWater == 0) {
			dfs(currentFullness / 2, 1);
		}
	}
}
