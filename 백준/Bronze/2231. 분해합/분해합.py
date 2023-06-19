import sys
input = sys.stdin.readline

n = int(input())

for i in range(1, n + 1):
    disit_sum = sum(map(int, str(i)))

    if n == i + disit_sum:
        print(i)
        break
    if n == i:
        print(0)
        break


