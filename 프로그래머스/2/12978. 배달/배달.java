import java.util.*;

class Solution {
    private static List<List<Node>> graph = new ArrayList<>();
    private static int[] distance;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        distance = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < road.length; i++) {
            int[] r = road[i];
            int A = r[0];
            int B = r[1];
            int C = r[2];
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        dijkstra(1);
                
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
    
    private void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((Comparator.comparingInt(n -> n.cost))); 
        pq.offer(new Node(start, 0));
        distance[start] = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curV = cur.v;
            int curCost = cur.cost;
            if (distance[curV] < curCost) {
                continue;
            }
            
            for (int i = 0; i < graph.get(curV).size(); i++) {
                int nextV = graph.get(curV).get(i).v;
                int nextCost = distance[curV] + graph.get(curV).get(i).cost;
                
                if (nextCost < distance[nextV]) {
                    distance[nextV] = nextCost;
                    pq.offer(new Node(nextV, nextCost));
                }
            }
        }
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