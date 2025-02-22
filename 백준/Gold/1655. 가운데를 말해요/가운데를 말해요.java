import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 최대 힙 (왼쪽 부분, 중간값 이하의 숫자들, top은 중간값)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위가 가장 높은 원소가 가장 먼저 나옴
        // 최소 힙 (오른쪽 부분, 중간값 초과의 숫자들)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            // 힙 크기 균형 맞추기
            if (maxHeap.size() == minHeap.size()) { // 홀수 번째
                maxHeap.offer(num);
            } else { // 짝수 번째
                minHeap.offer(num);
            }

            // 최대 힙의 최댓값이 최소 힙의 최솟값보다 크면 교환 (중간 값이 중간 값 다음보다 클 경우)
            if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(maxHeap.poll());
            }

            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.println(sb);
    }
}
