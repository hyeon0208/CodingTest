import sys
input = sys.stdin.readline


N, K = map(int, input().split())

nums = list(map(int, input().split()))

max_sum = sum(nums[:K])
result = []
result.append(max_sum)

for i in range(K, N):
    max_sum = max_sum + nums[i] - nums[i - K]
    result.append(max_sum)

print(max(result))
