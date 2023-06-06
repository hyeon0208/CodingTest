import sys
input = sys.stdin.readline

n = int(input())
cnt = 0

while n != 0:
    if n % 5 == 0:
        cnt += n // 5
        break
    elif n == 1 or n == 2:
        print(-1)
        exit(0)
    n -= 3
    cnt += 1

print(cnt)