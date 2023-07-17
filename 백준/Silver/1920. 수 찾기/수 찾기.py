import sys
input = sys.stdin.readline
from bisect import bisect_left, bisect_right

N = int(input())
N_nums = list(map(int, input().split()))
N_nums.sort() # 이분 탐색은 탐색하고자 하는 리스트가 정렬되어 있어야 한다.

M = int(input())
M_nums = list(map(int, input().split()))

def binarySearch(arr, val):
    left = bisect_left(arr, val)
    right = bisect_right(arr, val)
    return right - left


for i in range(M):
    if binarySearch(N_nums, M_nums[i]):
        print(1)
    else:
        print(0)
