import sys
input = sys.stdin.readline

n = int(input())
schedules = [list(map(int, input().split())) for _ in range(n)]

dp = [0 for _ in range(n + 1)]

# 상담 일정을 순회
for i in range(n): 
    # 상담을 진행할 수 있는 경우를 확인하고, 상담 후 날짜 범위를 순회
    for j in range(i + schedules[i][0], n + 1): 
        # 해당 일자에 이미 저장된 최대 수익이 현재 상담을 하고 얻을 수 있는 수익보다 작으면 최대 수익을 업데이트.
        if dp[j] < dp[i] + schedules[i][1]:
            dp[j] = dp[i] + schedules[i][1]

print(dp[-1])