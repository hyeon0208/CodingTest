import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> box = new HashMap<>();

        for (int num : tangerine) {
            box.put(num, box.getOrDefault(num, 0) + 1);
        }
        
        Map<Integer, Integer> sortedBox = box.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        for (Integer i : sortedBox.keySet()) {
            k -= sortedBox.get(i);
            answer++;
            if (k <= 0) {
                break;
            }
        }

        return answer;
    }
}