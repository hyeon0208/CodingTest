class Solution {
    public static int solution(int[] stones, int k) {
        int left = 1;
        int right = 200_000_000;

        while (left <= right) {
            int mid = (right + left) / 2;
            if (canAcross(stones, k, mid)) { // 건널수 있으면 더 많은 사람 시도
                left = mid + 1;
            } else { // 못건너면 더 적은 사람 시도
                right = mid - 1;
            }
        }
        return right;
    }

    private static boolean canAcross(int[] stones, int k, int n) {
        int continuousZeroCnt = 0;
        for (int stone : stones) {
            if (stone - n < 0) { // n명이 해당 돌을 밟아서 더는 못거너는 돌이 될 경우
                continuousZeroCnt++;
            } else { // 여기서 걸리면 연속된 0의 징검다리가 아님.
                continuousZeroCnt = 0;
            }
            if (continuousZeroCnt >= k) {
                return false;
            }
        }
        return true;
    }
}