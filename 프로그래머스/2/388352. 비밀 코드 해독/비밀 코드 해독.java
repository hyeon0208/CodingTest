import java.util.ArrayList;
import java.util.List;

class Solution {
    
    private static int answer;
    
    public static int solution(int n, int[][] q, int[] ans) {
        dfs(1, n, new ArrayList<>(n), q, ans);

        return answer;
    }

    private static void dfs(int start, int n, List<Integer> codes, int[][] q, int[] ans) {
        if (codes.size() == 5) {
            if (check(codes, q, ans)) {
                answer++;
            }
            return;
        }

        for (int i = start; i <= n; i++) {
            if (codes.contains(i)) {
                continue;
            }
            codes.add(i);
            dfs(i + 1, n, codes, q, ans);
            codes.remove(codes.size() - 1);
        }
    }

    private static boolean check(List<Integer> cods, int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int cnt = 0;
            for (int num : q[i]) {
                if (cods.contains(num)) {
                    cnt++;
                }
            }
            if (ans[i] != cnt) {
                return false;
            }
        }
        return true;
    }
}