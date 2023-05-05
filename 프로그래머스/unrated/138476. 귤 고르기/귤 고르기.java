import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }

        List<Integer> keyLists = new ArrayList<>(map.keySet());
        // value가 큰 순서대로 정렬
        keyLists.sort(((o1, o2) -> map.get(o2) - map.get(o1)));
        
        int i = 0;
        while (k > 0) {
            k -= map.get(keyLists.get(i));
            answer++;
            i++;
        }

        return answer;
    }
}