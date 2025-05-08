import java.util.*;

class Solution {
    
    private static List<List<Node>> nodes;
    
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        nodes = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int i = path[0];
            int j = path[1];
            int w = path[2];
            nodes.get(i).add(new Node(j, w));
            nodes.get(j).add(new Node(i, w));
        }
        
        return  dijkstra(n, gates, summits);
    }

    private static int[] dijkstra(int n, int[] gates, int[] summits) {
        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.w));
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        boolean[] isGate = new boolean[n + 1];
        boolean[] isSummit = new boolean[n + 1];

        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensity[gate] = 0;
            isGate[gate] = true;
        }

        for (int summit : summits) {
            isSummit[summit] = true;
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (intensity[cur.v] < cur.w) {
                continue;
            }

            if (isSummit[cur.v]) {
                continue;
            }

            for (Node nextNode : nodes.get(cur.v)) {
                if (isGate[nextNode.v]) {
                    continue;
                }

                int nextW = Math.max(cur.w, nextNode.w);
                if (intensity[nextNode.v] > nextW) {
                    intensity[nextNode.v] = nextW;
                    pq.offer(new Node(nextNode.v, nextW));
                }
            }
        }

        int summitV = 0;
        int minIntensity = Integer.MAX_VALUE;

        Arrays.sort(summits);
        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minIntensity = intensity[summit];
                summitV = summit;
            }
        }

        return new int[]{summitV, minIntensity};
    }

    private static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}