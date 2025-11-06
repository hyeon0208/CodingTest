import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int H;
	private static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		int cnt = T;

		int[] people = new int[N];
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(br.readLine());
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int person : people) {
			pq.offer(person);
		}

		int result = 0;
		while (cnt > 0) {
			if (pq.isEmpty()) {
				break;
			}

			if (pq.peek() == 1) {
				if (H == 1) {
					break;
				}
				result++;
				pq.poll();
				continue;
			}

			if (pq.peek() >= H) {
				pq.offer(pq.poll() / 2);
				cnt--;
			} else {
				result++;
				pq.poll();
			}
		}

		while (true) {
			if (!pq.isEmpty() && pq.peek() < H) {
				pq.poll();
				result++;
			} else {
				break;
			}
		}


		if (!pq.isEmpty()) {
			System.out.println("NO");
			System.out.println(pq.stream().max(Integer::compare).get());
			return;
		}
		System.out.println("YES");
		System.out.println(T - cnt);
	}
}
