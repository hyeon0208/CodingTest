import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] chains = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			chains[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(chains);

		int answer = 0;
		int left = 0;
		int right = n - 1;

		while (left < right) {
			// 왼쪽 체인에서 1개 끊기
			chains[left]--;
			answer++;

			// 오른쪽 체인 연결 완료
			right--;

			// 왼쪽 체인 다 쓰면 다음으로
			if (chains[left] == 0) {
				left++;
			}
		}

		System.out.println(answer);
	}
}