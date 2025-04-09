import java.util.*;

class Solution {
    
    private static int result = Integer.MAX_VALUE;
    
    public static int solution(int n, int[][] costs) {

        List<List<Node>> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int[] cost : costs) {
            int origin = cost[0];
            int target = cost[1];
            int c = cost[2];
            nodes.get(origin).add(new Node(target, c));
            nodes.get(target).add(new Node(origin, c));
        }

        return prim(nodes, 0, n);
    }

    private static int prim(List<List<Node>> nodes, int start, int n) {
        // 우선순위 큐 - 비용이 낮은 간선 우선
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        // 방문 여부 배열
        boolean[] visited = new boolean[n];

        // 시작 정점 처리
        pq.offer(new Node(start, 0));
        int totalCost = 0;
        int connectedNodes = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 이미 방문한 정점이면 스킵
            if (visited[current.v]) {
                continue;
            }

            // 방문 처리 및 비용 추가
            visited[current.v] = true;
            totalCost += current.cost;
            connectedNodes++;

            // 모든 정점을 방문했다면 종료
            if (connectedNodes == n) {
                break;
            }

            // 인접 정점 탐색
            for (Node next : nodes.get(current.v)) {
                // 방문하지 않은 정점만 큐에 추가
                if (!visited[next.v]) {
                    pq.offer(next);
                }
            }
        }

        return totalCost;
    }

    private static class Node {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", cost=" + cost +
                    '}';
        }
    }
}