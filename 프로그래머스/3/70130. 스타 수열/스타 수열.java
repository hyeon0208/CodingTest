import java.util.*;

class Solution {
    
    public static int solution(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : a) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int maxLength = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) * 2 <= maxLength) {
                continue;
            }
            int pairCount = 0;

            for (int i = 0; i < a.length - 1; i++) {
                if ((a[i] == key && a[i + 1] != key) || (a[i + 1] == key && a[i] != key)) {
                    pairCount++;
                    i++;
                }
                maxLength = Math.max(maxLength, pairCount * 2);
            }
        }

        return maxLength;
    }
}