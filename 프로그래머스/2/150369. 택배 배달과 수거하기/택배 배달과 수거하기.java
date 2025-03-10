class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliveryRemain = 0;
        int pickupRemain = 0;

        for (int i = n - 1; i >= 0; i--) {
            deliveryRemain += deliveries[i];
            pickupRemain += pickups[i];

            // 이 집까지 여러 번 방문이 필요한 경우 (둘 중 하나라도 작업이 남아있으면 트럭이 출발)
            while (deliveryRemain > 0 || pickupRemain > 0) {
                // 한 번 방문으로 처리할 수 있는 최대 양
                deliveryRemain -= cap;
                pickupRemain -= cap;

                // i번째 집까지 왕복 거리를 더함
                answer += (i + 1) * 2;
            }
        }
        return answer;
    }
}