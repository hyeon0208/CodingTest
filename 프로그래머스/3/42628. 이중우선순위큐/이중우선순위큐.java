import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {

    private static final String OFFER = "I";
    private static final String REMOVE = "D";
    
    public int[] solution(String[] operations) {
        int[] result = new int[2];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (String operation : operations) {
            String[] split = operation.split(" ");
            String command = split[0];
            int num = Integer.parseInt(split[1]);
            if (command.equals(OFFER)) {
                maxHeap.offer(num);
                minHeap.offer(num);
            } else if (command.equals(REMOVE)) {
                if (minHeap.isEmpty() || maxHeap.isEmpty()) {
                    continue;
                }

                if (num == 1) {
                    minHeap.remove(maxHeap.poll());
                } else {
                    maxHeap.remove(minHeap.poll());
                }
            }
        }

        if (maxHeap.isEmpty()) {
            return result;
        } {
            result[0] = maxHeap.peek();
            result[1] = minHeap.peek();
            return result;
        }
    }
}