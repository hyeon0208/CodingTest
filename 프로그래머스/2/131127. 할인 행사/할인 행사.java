import java.util.*;

// 슬라이딩 윈도우 O(n)
class Solution {
    final static int DISCOUNT_MATCH_DAY = 10;
    
    public int solution(String[] want, int[] number, String[] discount) {
        int result = 0;
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        for (int i = 0; i < discount.length - DISCOUNT_MATCH_DAY + 1; i++) {
            Map<String, Integer> buyMap = new HashMap<>();
            for (int j = i; j < i + DISCOUNT_MATCH_DAY; j++) {
                buyMap.put(discount[j], buyMap.getOrDefault(discount[j], 0) + 1);
            }
            
            boolean allMatch = true;
            for (String item : want) {
                if (!buyMap.getOrDefault(item, 0).equals(wantMap.get(item))) {
                    allMatch = false;
                }
            }
            
            if (allMatch) {
                result++;
            }
        }
                
        return result;
    }
}