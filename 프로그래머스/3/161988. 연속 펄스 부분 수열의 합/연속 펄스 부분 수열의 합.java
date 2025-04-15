class Solution {
    public static long solution(int[] sequence) {
        int n = sequence.length;

        if (n == 1) {
            return Math.max(sequence[0], -sequence[0]);
        }

        long[] dp1 = new long[n]; // 1, -1, 1, -1, ... 패턴
        long[] dp2 = new long[n]; // -1, 1, -1, 1, ... 패턴

        dp1[0] = sequence[0];
        dp2[0] = -sequence[0];

        long maxSum = Math.max(dp1[0], dp2[0]);

        for (int i = 1; i < n; i++) {
            // i가 짝수인 경우 1, 홀수인 경우 -1을 곱함 (1로 시작하는 펄스)
            int pulse1 = (i % 2 == 0) ? 1 : -1;

            // i가 짝수인 경우 -1, 홀수인 경우 1을 곱함 (-1로 시작하는 펄스)
            int pulse2 = (i % 2 == 0) ? -1 : 1;

            // i번째 원소에서 새로 시작하는 경우, 이전까지의 최대 합에 현재 원소를 추가하는 경우 중 최대값 선택
            dp1[i] = Math.max(sequence[i] * pulse1, dp1[i-1] + sequence[i] * pulse1);
            dp2[i] = Math.max(sequence[i] * pulse2, dp2[i-1] + sequence[i] * pulse2);

            maxSum = Math.max(maxSum, Math.max(dp1[i], dp2[i]));
        }
        return maxSum;
    }
}