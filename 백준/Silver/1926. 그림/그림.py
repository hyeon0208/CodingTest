from collections import deque
import sys
input = sys.stdin.readline

n, m = map(int, input().split()) #행, 열
paper = [list(map(int, input().split())) for _ in range(n)]
visited = [[False] * m for _ in range(n)]

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x, y):
    q = deque()
    q.append((x, y))
    visited[x][y] = True
    area = 1

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < m:
                if not visited[nx][ny] and paper[nx][ny] == 1:
                    q.append((nx, ny))
                    visited[nx][ny] = True
                    area += 1
    return area

painting = []

for x in range(n):
    for y in range(m):
        if paper[x][y] == 1 and not visited[x][y]:
            painting.append(bfs(x, y))

if len(painting) == 0:
    print(len(painting))
    print(0)
else:
    print(len(painting))
    print(max(painting))