import sys
input = sys.stdin.readline

n = int(input())
ropes = []
for _ in range(n):
    ropes.append(int(input()))
ropes.sort()

answers = []
for r in ropes:
    answers.append(r*n)
    n -= 1
print(max(answers))