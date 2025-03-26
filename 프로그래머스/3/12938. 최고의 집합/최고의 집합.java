import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[]{-1};
        }

        int[] answer = new int[n];
        int quotient = s / n;
        int remainder = s % n;

        Arrays.fill(answer, quotient);

        for (int i = n - 1; i >= n - remainder; i--) {
            answer[i]++;
        }

        return answer;
    }
}