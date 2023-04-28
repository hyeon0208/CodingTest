import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.stream(scoville).forEach(n -> pq.add(n));

        if (scoville.length < 2) {
            return -1;
        }
        
        while (pq.peek() < K) {
            if (pq.size() == 1) {
                return -1;
            }

            int min1 = pq.poll();
            int min2 = pq.poll();

            pq.add(min1 + (min2 * 2));
            answer++;
        }

        return answer;
    }
}