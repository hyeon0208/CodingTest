import sys
input = sys.stdin.readline

import itertools

n = int(input())

a = list(map(int, input().split()))
b = list(map(int, input().split()))

a.sort(reverse=True)
b.sort()

result = 0
for i in range(n):
    result += a[i] * b[i]

print(result)