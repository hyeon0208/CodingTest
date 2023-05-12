from collections import deque
import sys
input = sys.stdin.readline

N = int(input())
map = [list(map(int, input().split())) for _ in range(N)]

maxNum = 0
for line in map:
    maxNum = max(max(line), maxNum)

def bfs(h, x, y):
    q = deque()
    q.append((x, y))
    visited[x][y] = True

    dx = [1, -1 ,0, 0]
    dy = [0, 0, -1, 1]

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and map[nx][ny] > h:
                q.append((nx, ny))
                visited[nx][ny] = True

results = []

for h in range(maxNum):
    cnt = 0
    visited = [[False] * N for _ in range(N)]
    for x in range(N):
        for y in range(N):
            if map[x][y] > h and not visited[x][y]:
                bfs(h, x, y)
                cnt += 1
    results.append(cnt)

print(max(results))