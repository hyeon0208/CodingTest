import java.util.*;

class Solution {
    
    private static List<String> results;
    
    public static String[] solution(String[][] tickets) {
        boolean[] visited = new boolean[tickets.length];
        results = new ArrayList<>();
        dfs("ICN", "ICN", 0, tickets, visited);
        Collections.sort(results);
        return results.get(0).split(" ");
    }

    private static void dfs(String current, String path, int depth, String[][] tickets, boolean[] visited) {
        if (depth == tickets.length) {
            results.add(path);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (current.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], depth + 1, tickets, visited);
                visited[i] = false;
            }
        }
    }
}