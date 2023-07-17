import sys
input = sys.stdin.readline
from collections import Counter

N = int(input())
N_nums = list(map(int, input().split()))

M = int(input())
M_nums = list(map(int, input().split()))

dict = Counter(N_nums)

for num in M_nums:
    if num in dict:
        print(dict[num], end=' ')
    else:
        print(0, end=' ')