import java.util.*;

public class Solution {
    public int[] solution(int []arr) {  
        List<Integer> answer = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        
        for (Integer next : arr) {
            if (stack.isEmpty()) {
                stack.push(next);
            }
            
            if (stack.peek() != next) {
                stack.push(next);
            }
            
        }
        
        
        return stack.stream().mapToInt(Integer::intValue).toArray();
    }
}