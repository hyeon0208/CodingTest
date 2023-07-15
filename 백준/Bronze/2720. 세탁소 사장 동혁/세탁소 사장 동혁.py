import sys
input = sys.stdin.readline

T = int(input())

moneys = [25, 10, 5, 1]
result = []

for _ in range(T):
    c = int(input())

    for m in moneys:
        print(c // m, end=' ')
        c = c % m