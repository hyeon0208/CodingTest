class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        for i in range(2, len(cost)):
            # 한 칸, 두 칸 오르는 비용 중 제일 작은 비용만큼을 지불해간다.
            cost[i] += min(cost[i - 1], cost[i - 2]) 
        # 마지막에 도달 했을 때 마지막 계단, 마지막 전 계단의 도착 비용 중 제일 작은 값을 반환한다.
        return min(cost[-2], cost[-1]) 