import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        
        int start = 0;
        
        for (int i = 0; i < number.length() - k; i++) {
            int max = 0;
            for (int j = start; j <= i + k; j++) {
                int current = number.charAt(j) - '0';
                // 가장 큰수를 골라서 그 다음 인덱스를 시작 인덱스로 설정
                if (max < current) {
                    max = current;
                    start = j + 1;
                }                
            }
            answer.append(max);
        }
        return answer.toString();
    }
}