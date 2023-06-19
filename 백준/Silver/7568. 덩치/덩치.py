import sys
input = sys.stdin.readline

n = int(input())
info = [list(map(int, input().split())) for _ in range(n)]
result = []

for i in range(n):
    rank = 1
    for j in range(n):
        if info[i][0] < info[j][0] and info[i][1] < info[j][1]:
            rank += 1
    result.append(rank)

for a in result:
    print(a, end=" ")
