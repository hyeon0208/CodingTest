import sys
input = sys.stdin.readline

N, M = map(int, input().split())

nums = list(map(int, input().split()))

sum = nums[0]
start = 0
end = 1
cnt = 0 

while True:
    if sum >= M:
        if sum == M:
            cnt += 1
        sum -= nums[start]
        start += 1

    elif end == len(nums):
        break

    elif sum < M:
        sum += nums[end]
        end += 1

print(cnt)