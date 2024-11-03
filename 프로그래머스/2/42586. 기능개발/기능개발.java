import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> results = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < progresses.length; i++) {
            int day = (int) Math.ceil((100 - progresses[i]) / (double) speeds[i]);
            queue.offer(day);
        }
        
        while(!queue.isEmpty()) {
            int curDay = queue.poll();
            int count = 1;
           while (!queue.isEmpty() && queue.peek() <= curDay) {
                queue.poll();
                count++;
            }

            results.add(count);
        }

        
        return results.stream().mapToInt(i -> i).toArray();
    }
}