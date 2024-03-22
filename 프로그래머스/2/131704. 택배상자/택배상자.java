import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> sub = new Stack<>();        
            
        int index = 0;
        for(int i = 1 ; i <= order.length; i++){
            if (order[index] == i) {
                index++;
                answer++;
            } else {
                sub.push(i);
            }
            while (!sub.isEmpty() && sub.peek() == order[index]) {
                sub.pop();
                index++;
                answer++;
            }
        }

        return answer;
    }
}