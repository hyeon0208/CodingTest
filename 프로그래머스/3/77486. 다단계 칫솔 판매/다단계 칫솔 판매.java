import java.util.*;

class Solution {
    
    private static final int SELL_PRICE = 100;
    
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            indexMap.put(enroll[i], i);
        }


        Map<String, String> referralMap = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            referralMap.put(enroll[i], referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            dfs(seller[i], amount[i] * SELL_PRICE, referralMap, indexMap, answer);
        }
        
        return answer; // 각 판매원의 이익금의 총합 + enroll에 이름이 포함된 순서에 따라 나열
    }

    private static void dfs(String name, int profit, Map<String, String> referralMap, Map<String, Integer> indexMap, int[] answer) {
        if (name.equals("-") || profit == 0) {
            return;
        }
        int referProfit = (int) (profit * 0.1);
        int myProfit = profit - referProfit;

        answer[indexMap.get(name)] += myProfit;

        // 추천인에게 이익 분배 (10%가 1원 미만이면 이익 분배하지 않음)
        if (referProfit > 0) {
            dfs(referralMap.get(name), referProfit, referralMap, indexMap, answer);
        }
    }
}