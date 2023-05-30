from collections import deque
import sys
input = sys.stdin.readline

n, l, r = map(int, input().split())

country = [list(map(int, input().split())) for _ in range(n)]

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]
cnt = 0

def bfs(x, y):
    q = deque()
    q.append((x, y))
    visited[x][y] = True
    union = [(x, y)]

    while q:
        x, y = q.popleft()

        for i in range(4):
            move = True
            nx = dx[i] + x
            ny = dy[i] + y

            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
                if l <= abs(country[nx][ny] - country[x][y]) <= r:
                    q.append((nx, ny))
                    visited[nx][ny] = True
                    union.append((nx, ny))

    return union
    

while True:
    visited = [[False] * n for _ in range(n)]
    move = False

    for x in range(n):
        for y in range(n):
            if not visited[x][y]:
                union = bfs(x, y)
                
                if len(union) > 1:
                    move = True
                    population = sum(country[i][j] for i, j in union) // len(union)

                    for i, j in union:
                        country[i][j] = population

    if not move:
        break
    cnt += 1

print(cnt)