import java.util.ArrayList;
import java.util.List;

class Solution {
    
    private static final int SHEEP = 0;
    private static final int WOLF = 1;

    private static int maxSheep = 0;
    private static List<List<Integer>> graph;
    
    public static int solution(int[] info, int[][] edges) {
        int n = info.length;

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            graph.get(parent).add(child);
        }

        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);

        backtrack(0, 0, nextNodes, info);

        return maxSheep;
    }

    private static void backtrack(int sheepCnt, int wolfCnt, List<Integer> nextNodes, int[] info) {
        maxSheep = Math.max(maxSheep, sheepCnt);

        for (int i = 0; i < nextNodes.size(); i++) {
            int current = nextNodes.get(i);
            int newSheepCnt = sheepCnt;
            int newWolfCnt = wolfCnt;
            if (info[current] == SHEEP) {
                newSheepCnt++;
            } else {
                newWolfCnt++;
            }
            if (newWolfCnt >= newSheepCnt) {
                continue;
            }
            List<Integer> newNextNodes = new ArrayList<>(nextNodes);
            newNextNodes.remove(i);

            newNextNodes.addAll(graph.get(current));

            backtrack(newSheepCnt, newWolfCnt, newNextNodes, info);
        }
    }
}