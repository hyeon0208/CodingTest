import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	private static List<List<Node>> nodes;
	private static int n;

	public static void main(String[] args) throws IOException { // https://www.acmicpc.net/problem/11779
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 정점 개수
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine()); // 간선 개수

		nodes = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; i++) {
			nodes.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodes.get(x).add(new Node(y, cost));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		System.out.println(dijkstra(start, end));
	}

	private static String dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
		pq.offer(new Node(start, 0));
		int[] parent = new int[n + 1]; // 경로 추적을 위한 부모 배열
		Arrays.fill(parent, -1);
		int[] distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (distance[cur.v] < cur.cost) {
				continue;
			}
			if (cur.v == end) {
				break;
			}

			for (Node next : nodes.get(cur.v)) {
				int nextCost = next.cost + cur.cost;
				if (distance[next.v] > nextCost) {
					distance[next.v] = nextCost;
					pq.offer(new Node(next.v, nextCost));
					parent[next.v] = cur.v; // 부모 정점 기록
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(distance[end]).append("\n");

		Stack<Integer> path = new Stack<>();
		int current = end;
		while (current != -1) {
			path.push(current);
			current = parent[current];
		}
		sb.append(path.size()).append("\n");

		while (!path.isEmpty()) {
			sb.append(path.pop()).append(" ");
		}

		return sb.toString();
	}

	private static class Node {
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
}