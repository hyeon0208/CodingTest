import sys
input = sys.stdin.readline

arr = input().split('-')

num = 0
for i in arr[0].split('+'):
    num += int(i)
for i in arr[1:]:
  num -= sum(map(int, (i.split('+'))))
print(num)
