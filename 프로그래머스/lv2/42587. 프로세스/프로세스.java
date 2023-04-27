import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        PriorityQueue<Integer> prints = new PriorityQueue<>(Comparator.reverseOrder());
        Arrays.stream(priorities).forEach(n -> prints.add(n));
        
        while (!prints.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (prints.peek() == priorities[i]) {
                    prints.poll();
                    answer++;
                    if (location == i) {
                        return answer;
                    }
                }
            }
        }
        return answer;
    }
}