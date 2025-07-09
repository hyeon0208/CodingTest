import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException { // https://www.acmicpc.net/problem/1004
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int n = Integer.parseInt(br.readLine());
			int count = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int cx = Integer.parseInt(st.nextToken());
				int cy = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());

				boolean start_in = isInCircle(x1, y1, cx, cy, r);
				boolean end_in = isInCircle(x2, y2, cx, cy, r);

				if (start_in != end_in) {
					count++;
				}
			}
			sb.append(count).append("\n");
		}

		System.out.println(sb);
	}

	// 원 안,밖 판별 : (x - cx)² + (y - cy)² < r² = true면 원 안
	private static boolean isInCircle(int x, int y, int cx, int cy, int r) {
		// 1. x축, y축 방향 차이 계산
		int dx = x - cx;
		int dy = y - cy;

		// 2. 거리의 제곱 계산 (피타고라스 정리)
		int distanceSquared = dx * dx + dy * dy;

		// 3. 반지름의 제곱과 비교
		return distanceSquared < r * r;
	}
}