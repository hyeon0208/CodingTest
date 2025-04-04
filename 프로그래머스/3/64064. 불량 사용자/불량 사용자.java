import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private static Set<Set<String>> result = new HashSet<>();
    private static List<List<String>> matchingIds = new ArrayList<>();

    public static int solution(String[] user_id, String[] banned_id) {
        for (String banned : banned_id) {
            List<String> matches = new ArrayList<>();
            for (String user : user_id) {
                if (matches(user, banned)) {
                    matches.add(user);
                }
            }
            matchingIds.add(matches);
        }

        System.out.println(matchingIds);

        dfs(0, new HashSet<>());

        System.out.println(result);

        return result.size();
    }

    private static boolean matches(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false;
        }

        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) != '*' && bannedId.charAt(i) != userId.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int depth, Set<String> selected) {
        if (depth == matchingIds.size()) {
            result.add(new HashSet<>(selected));
            return;
        }

        for (String matchId : matchingIds.get(depth)) {
            if (!selected.contains(matchId)) {
                selected.add(matchId);
                dfs(depth + 1, selected);
                selected.remove(matchId);
            }
        }
    }
}