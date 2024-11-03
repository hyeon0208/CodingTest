import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] results = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int prevIndex = stack.pop();
                results[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int prevIndex = stack.pop();
            results[prevIndex] = prices.length - 1 - prevIndex;
        }
        
        return results;
    }
}