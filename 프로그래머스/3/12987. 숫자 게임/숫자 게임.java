import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int bIdx = B.length - 1;
        for (int i = B.length - 1; i >= 0; i--) {
            if (B[bIdx] > A[i]) {
                bIdx--;
                answer++;
            }
        }

        return answer;
    }
}