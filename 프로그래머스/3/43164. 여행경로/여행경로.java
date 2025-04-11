import java.util.*;

class Solution {
    
    private static List<String> results;
    
    public static String[] solution(String[][] tickets) {
        Map<String, Integer> map = new HashMap<>();
        for (String[] ticket : tickets) {
            map.put(ticket[0], map.getOrDefault(ticket[0], 0) + 1);
            map.put(ticket[1], map.getOrDefault(ticket[1], 0) + 1);
        }

        results = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append("ICN").append(" ");
        boolean[] visited = new boolean[tickets.length];
        backTracking(0, tickets, visited, sb, map, "ICN");
        Collections.sort(results);            

        return results.get(0).split(" ");
    }

    private static void backTracking(int depth, String[][] tickets, boolean[] visited, StringBuilder sb, Map<String, Integer> map, String prev) {
        if (depth == tickets.length) {
            results.add(sb.toString());
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            String[] ticket = tickets[i];
            if (prev.equals(ticket[0]) && !visited[i]) {
                map.put(ticket[0], map.getOrDefault(ticket[0], 0) - 1);
                map.put(ticket[1], map.getOrDefault(ticket[1], 0) - 1);
                sb.append(ticket[1]).append(" ");
                visited[i] = true;
                backTracking(depth + 1, tickets, visited, sb, map, ticket[1]);
                visited[i] = false;
                int len = ticket[1].length();
                sb.delete(sb.length() - len - 1, sb.length());
            }
        }
    }
}