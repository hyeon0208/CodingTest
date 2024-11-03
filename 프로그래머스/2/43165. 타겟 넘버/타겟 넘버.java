import java.util.*;

class Solution {
    int result = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);

        return result;
    }
    
    private void dfs(int[] numbers, int target, int depth, int sum) {        
        if (numbers.length == depth) {
            if (sum == target) {
                result++;
                return;
            }
            return;
        }
        
        dfs(numbers, target, depth + 1, sum + numbers[depth]);
        dfs(numbers, target, depth + 1, sum - numbers[depth]);
    }
}