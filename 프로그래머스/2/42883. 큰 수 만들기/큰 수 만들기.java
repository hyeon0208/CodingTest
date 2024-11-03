import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String result = "";
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < number.length(); i++) {
            char current = number.charAt(i);

            while (!stack.isEmpty() && k > 0 && stack.peek() < current) {
                stack.pop();
                k--;
            }
            
            stack.push(current);
        }
        
        // k가 남아있다면 스택의 뒤에서부터 k개 제거
        while (k > 0) {
            stack.pop();
            k--;
        }
        
      StringBuilder sb = new StringBuilder();
        for(Character ch : stack) {
            sb.append(ch);
        }
        
        return sb.toString();
    }
}