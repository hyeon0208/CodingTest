import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static int v;
	private static int e;
	private static List<List<Node>> graph;

	public static void main(String[] args) throws IOException { // https://www.acmicpc.net/problem/1197
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(v + 1);
		for (int i = 0; i <= v; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, cost));
			graph.get(b).add(new Node(a, cost));
		}

		System.out.println(mst(1));
	}

	private static int mst(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
		boolean[] visited = new boolean[v + 1];

		pq.offer(new Node(start, 0));
		int totalCost = 0;
		int connectedNodes = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (visited[cur.v]) {
				continue;
			}

			visited[cur.v] = true;
			totalCost += cur.cost;
			connectedNodes++;

			if (connectedNodes == v) {
				break;
			}

			for (Node next : graph.get(cur.v)) {
				if (!visited[next.v]) {
					pq.offer(next);
				}
			}
		}

		return totalCost;
	}

	private static class Node {
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
}