import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] servers = new int[24];

        for (int i = 0; i < players.length; i++) {
            int needServerCnt = players[i] / m;
            
            if (needServerCnt == 0) {
                continue;
            }
            
            if (servers[i] >= needServerCnt) {
                continue;
            }
            
            int add = needServerCnt - servers[i];
            answer += add;
            
            int end = Math.min(24, i + k);
            for (int j = i + 1; j < end; j++) {
                servers[j] += add;
            }
        }
    
        return answer;
    }
}