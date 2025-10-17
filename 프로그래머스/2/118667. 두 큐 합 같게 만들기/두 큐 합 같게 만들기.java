class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;

        // 1. 두 큐의 초기 합 계산
        long sum1 = 0, sum2 = 0;
        for (int val : queue1) sum1 += val;
        for (int val : queue2) sum2 += val;

        // 2. 전체 합이 홀수면 불가능
        long totalSum = sum1 + sum2;
        if (totalSum % 2 != 0) return -1;

        long target = totalSum / 2;

        // 3. 이미 같으면 0 반환
        if (sum1 == target) return 0;

        // 4. 두 큐를 하나의 배열로 연결
        int[] arr = new int[n * 2];
        for (int i = 0; i < n; i++) {
            arr[i] = queue1[i];
            arr[i + n] = queue2[i];
        }

        // 5. 투 포인터로 구간 합 조정
        int left = 0;           // queue1의 시작
        int right = n - 1;      // queue1의 끝
        int count = 0;
        int limit = n * 3;      // 최대 작업 횟수

        while (count <= limit) {
            if (sum1 == target) {
                return count;
            } else if (sum1 < target) {
                right++;
                if (right >= n * 2) break;
                sum1 += arr[right];
                count++;
            } else {
                sum1 -= arr[left];
                left++;
                if (left > right) break;
                count++;
            }
        }
        return -1;
    }
}