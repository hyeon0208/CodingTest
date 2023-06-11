t = int(input()) #테스트 케이스 개수
arr = []
for _ in range(t):
  n, m = map(int, input().split())
  arr = list(enumerate(list(map(int, input().split()))))
  v = arr[m]
  idx = 0
  while len(arr):
    max_v = max([i[1] for i in arr])
    if arr[0][1] == max_v:
      now = arr.pop(0)
      idx += 1
      if now == v:
        print(idx)
        break
    else:
      now = arr.pop(0)
      arr.append(now)