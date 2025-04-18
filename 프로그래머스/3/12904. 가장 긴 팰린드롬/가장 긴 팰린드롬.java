import java.util.*;

class Solution {
    
    public static int solution(String s) {
        int maxLength = 1;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // 홀수 길이 팰린드롬 확인 (aba)
            int len2 = expandAroundCenter(s, i, i + 1); // 짝수 길이 팰린드롬 확인 (abba)
            int longerLength = Math.max(len1, len2);
            maxLength = Math.max(maxLength, longerLength);
        }

        return maxLength;
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 종료 시점에서 left와 right 값은 실제 팰린드롬 범위를 벗어난 상태이기에 복구
        return (right - 1) - (left + 1) + 1;
    }
}