import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException { // https://www.acmicpc.net/problem/14002
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N];
		int[] parent = new int[N];
		int maxLength = 1;
		int maxIndex = 0;
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			parent[i] = -1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					if (dp[j] + 1 > dp[i]) {
						dp[i] = dp[j] + 1;
						parent[i] = j;
					}
				}
			}
			if (dp[i] > maxLength) {
				maxLength = dp[i];
				maxIndex = i;
			}
		}

		List<Integer> result = new ArrayList<>();
		int current = maxIndex;
		while (current != -1) {
			result.add(arr[current]);
			current = parent[current];
		}
		Collections.reverse(result);

		System.out.println(maxLength);
		for (int i = 0; i < result.size(); i++) {
			if (i > 0) System.out.print(" ");
			System.out.print(result.get(i));
		}
		System.out.println();
	}
}