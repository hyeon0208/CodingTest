import java.util.*;

class Solution {
    private final static int MEMBERSHIP_VLAID_DAY = 10;
    
    public int solution(String[] want, int[] number, String[] discount) {
        int result = 0;
            
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        for (int i = 0; i < MEMBERSHIP_VLAID_DAY; i++) {
            if (wantMap.containsKey(discount[i])) {
                wantMap.put(discount[i], wantMap.get(discount[i]) - 1);
            }
        }
        
        if (allMatch(wantMap)) {
            result++;
        }
        
        for (int i = MEMBERSHIP_VLAID_DAY; i < discount.length; i++) {
            if (wantMap.containsKey(discount[i - MEMBERSHIP_VLAID_DAY])) {
                wantMap.put(discount[i - MEMBERSHIP_VLAID_DAY], wantMap.get(discount[i - MEMBERSHIP_VLAID_DAY]) + 1);
            }
            
            if (wantMap.containsKey(discount[i])) {
                wantMap.put(discount[i], wantMap.get(discount[i]) - 1);
            }
            
            if (allMatch(wantMap)) {
                result++;
            }   
        }
        return result;
    }
    
    private boolean allMatch(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                return false;
            }
        }
        return true;
    }
}