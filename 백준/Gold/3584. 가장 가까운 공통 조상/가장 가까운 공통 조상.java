import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException { // https://www.acmicpc.net/problem/3584
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] nodes = new int[N + 1];
			for (int i = 0; i < N - 1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				nodes[child] = parent;
			}

			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			Set<Integer> ancestors = new HashSet<>();
			int current = a;
			while (current != 0) {
				ancestors.add(current);
				current = nodes[current];
			}

			current = b;
			while (current != 0) {
				if (ancestors.contains(current)) {
					sb.append(current).append("\n");
					break;
				}
				current = nodes[current];
			}
		}
		System.out.println(sb);
	}
}