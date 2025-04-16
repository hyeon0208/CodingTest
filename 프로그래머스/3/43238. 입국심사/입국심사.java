import java.util.*;

class Solution {
    public static long solution(int n, int[] times) {
        Arrays.sort(times);

        long left = 1;
        long right = (long) times[0] * n;

        while (left < right) {
            long mid = (left + right) / 2;
            long people = countPeople(mid, times);

            if (people >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // 주어진 시간 내에 처리할 수 있는 총 사람 수
    private static long countPeople(long time, int[] times) {
        long count = 0;
        for (int t : times) {
            count += time / t;
        }
        return count;
    }
}