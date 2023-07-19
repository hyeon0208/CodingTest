import sys
input = sys.stdin.readline

N, S = map(int, input().strip().split())
nums = list(map(int, input().strip().split()))

total = 0 # 부분합
start = 0
end = 0
min_len = 1e9

while True:
    # 현재 부분합이 찾고자하는 값보다 크거나 같다면
    if total >= S: 
        min_len = min(min_len, end - start) # 가장 짧은 길이 갱신
        total -= nums[start]
        start += 1

    # 현재 부분합이 찾고자하는 값보다 작다면
    elif total < S:
        if end == len(nums):    # 배열의 끝까지 탐색했다면 종료
            break
        total += nums[end]
        end += 1

if min_len == 1e9:
    print(0)
else:
    print(min_len)