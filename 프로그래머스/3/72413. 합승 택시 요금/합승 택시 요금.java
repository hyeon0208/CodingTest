import java.util.*;

class Solution {
    
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<Node>> nodes = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }
        for (int i = 0; i < fares.length; i++) {
            int[] fare = fares[i];
            nodes.get(fare[0]).add(new Node(fare[1], fare[2]));
            nodes.get(fare[1]).add(new Node(fare[0], fare[2]));
        }

        int[] fromStart = dijkstra(s, n, nodes); // S에서 모든 지점까지의 최단 거리
        int minCost = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            int[] fromI = dijkstra(i, n, nodes); // 지점 i에서 모든 다른 지점까지의 최단 거리
            int cost = fromStart[i] + fromI[a] + fromI[b]; // 출방점에서 i까지 함께 가고, 각자 a와 b로 가는 경우
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    private static int[] dijkstra(int start, int n, List<List<Node>> nodes) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(value -> value.cost));
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (distance[cur.v] < cur.cost) {
                continue;
            }
            for (Node nextNode : nodes.get(cur.v)) {
                int nextCost = cur.cost + nextNode.cost;
                if (distance[nextNode.v] > nextCost) {
                    distance[nextNode.v] = nextCost;
                    pq.offer(new Node(nextNode.v, nextCost));
                }
            }
        }
        return distance;
    }

    private static class Node {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}