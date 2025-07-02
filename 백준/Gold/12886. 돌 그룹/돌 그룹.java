import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/12886

	private static boolean[][] visited = new boolean[1501][1501];

	private static boolean result = false;
	private static int total;
	private static int target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		total = 0;
		int[] groups = new int[3];
		for (int i = 0; i < 3; i++) {
			groups[i] = Integer.parseInt(st.nextToken());
			total += groups[i];
		}

		if (total % 3 != 0) { // 균등 분배가 안될 경우
			System.out.println(0);
			return;
		}

		// 균등 분배한 값
		target = total / 3;

		dfs(groups[0], groups[1]);
		dfs(groups[1], groups[2]);
		dfs(groups[0], groups[2]);

		System.out.println(result ? 1 : 0);
	}

	private static void dfs(int a, int b) {
		if (a == target && b == target) {
			result = true;
			return;
		}
		if (result || visited[a][b] || visited[b][a]) {
			return;
		}

		visited[a][b] = true;

		int remain = total - a - b;
		if (a < b) {
			b -= a;
			a *= 2;
		} else {
			a -= b;
			b *= 2;
		}

		dfs(a, b);
		dfs(b, remain);
		dfs(a, remain);
	}
}
