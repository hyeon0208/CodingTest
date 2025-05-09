import java.util.*;

class Solution {
    
    public static int solution(int n, int[] cores) {
        int left = 0;
        int right = n * 10_000;
        int jobsLeftCount = n - cores.length;

        // n번째 작업을 처리 완료하기 위해 필요한 최소 시간 계산
        while (left < right) {
            int mid = (left + right) / 2;

            // mid 시간까지 처리한 작업 수 계산
            int completeJobCount = getCompleteJobCount(mid, cores);

            if (completeJobCount >= jobsLeftCount) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // n번째 작업이 아직 처리되지 않은 상태에 완료된 작업 수
        int completeJobCount = getCompleteJobCount(left - 1, cores);
        int remainJobCount = jobsLeftCount - completeJobCount;
        for (int i = 0; i < cores.length; i++) {
            if (left % cores[i] == 0) {
                remainJobCount--;
            }
            if (remainJobCount == 0) {
                return i + 1;
            }
        }

        return -1;
    }

    private static int getCompleteJobCount(int time, int[] cores) {
        int count = 0;
        for (int core : cores) {
            count += time / core;
        }
        return count;
    }
}