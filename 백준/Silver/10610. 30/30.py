import sys
input = sys.stdin.readline

n = input().strip()
n = sorted(n, reverse=True)

result = ""

for i in n:
    result += i

result = int(result)
if result % 30 == 0:
    print(result)
else:
    print(-1)