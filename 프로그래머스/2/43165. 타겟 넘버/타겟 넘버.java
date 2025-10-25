import java.util.*;

class Solution {
    private static int result = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);
        return result;
    }
    
    private void dfs(int[] numbers, int depth, int target, int sum) {                                          
        if (depth == numbers.length) {
            if (sum == target) {
                result++;
            }
            return;
        }
        
        dfs(numbers, depth + 1, target, sum + numbers[depth]);
        dfs(numbers, depth + 1, target, sum - numbers[depth]);
    }
}