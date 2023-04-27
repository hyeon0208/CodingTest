import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = bridge_length;
        int sum = 0;
        int time = 0;
        Queue<Integer> onBridge = new LinkedList<>();

        for (int truck : truck_weights) {
            while (true) {
                if (onBridge.isEmpty()) {
                    onBridge.add(truck);
                    sum += truck;
                    time++;
                    break;
                }  else if (onBridge.size() == bridge_length) {
                    sum -= onBridge.poll();
                } else if (onBridge.size() != bridge_length) {
                    if (sum + truck <= weight) {
                        onBridge.add(truck);
                        sum += truck;
                        time++;
                        break;
                    } else {
                        // 넘는다면 0을 넣어 이미 큐에 있는 트럭이 다리를 건너게 만듬
                        onBridge.add(0);
                        time++;
                    }
                }
            }
        }
        answer += time;
        
        return answer;
    }
}