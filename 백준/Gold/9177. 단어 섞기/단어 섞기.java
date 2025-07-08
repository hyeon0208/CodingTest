import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException { // https://www.acmicpc.net/problem/9177
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String word1 = st.nextToken();
			String word2 = st.nextToken();
			String word3 = st.nextToken();

			sb.append(String.format("Data set %d: %s", i + 1, bfs(word1, word2, word3))).append("\n");
		}

		System.out.println(sb);
	}

	private static String bfs(String word1, String word2, String word3) {
		if (word1.length() + word2.length() != word3.length()) {
			return "no";
		}

		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[word1.length() + 1][word2.length() + 1];
		queue.offer(new int[] {0, 0});
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int aIdx = cur[0];
			int bIdx = cur[1];

			// 두 단어를 모두 완전히 사용했다면 성공
			if (aIdx == word1.length() && bIdx == word2.length()) {
				return "yes";
			}

			int targetIdx = aIdx + bIdx;

			// word1의 다음 문자를 사용할 수 있는지 확인
			if (aIdx < word1.length() && word1.charAt(aIdx) == word3.charAt(targetIdx) && !visited[aIdx + 1][bIdx]) {
				visited[aIdx + 1][bIdx] = true;
				queue.offer(new int[]{aIdx + 1, bIdx});
			}

			// word2의 다음 문자를 사용할 수 있는지 확인
			if (bIdx < word2.length() && word2.charAt(bIdx) == word3.charAt(targetIdx) && !visited[aIdx][bIdx + 1]) {
				visited[aIdx][bIdx + 1] = true;
				queue.offer(new int[]{aIdx, bIdx + 1});
			}
		}

		return "no";
	}
}