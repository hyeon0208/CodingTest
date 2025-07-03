import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/2166

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		long[] x = new long[N + 1];
		long[] y = new long[N + 1];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}

		x[N] = x[0];
		y[N] = y[0];

		double ans = 0;
		for (int i = 0; i < N; i++) {
			ans += x[i] * y[i + 1] - x[i + 1] * y[i];
		}
		ans = Math.abs(ans) / 2.0;
		System.out.println(String.format("%.1f", ans));
	}
}