import sys
input = sys.stdin.readline

n = int(input())
line = list(map(int, input().split()))

temp = []

for i in range(n):
    temp.append((i, line[i]))

sortedList = sorted(temp, key=lambda x : x[1])

result = 0
time = []
for _, i in sortedList:
    i += result
    result = i
    time.append(i)

print(sum(time))