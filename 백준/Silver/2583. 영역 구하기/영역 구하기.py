from collections import deque
import sys
input = sys.stdin.readline

M, N, K = map(int, input().split()) # 행, 열, 직사각형 개수
paper = [[0] * N for _ in range(M)]
visited = [[False] * N for _ in range(M)]
results = []

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]

for _ in range(K):
    x1, y1, x2, y2 = map(int, input().split())
    for y in range(y1, y2):
        for x in range(x1, x2):
            paper[y][x] = 1

def bfs(x, y):
    q = deque()
    q.append((x, y))
    visited[x][y] = True

    cnt = 1

    while q:
        x, y = q.popleft()
        
        for i in range(4):
            nx = dx[i] + x
            ny = dy[i] + y

            if 0 <= nx < M and 0 <= ny < N and paper[nx][ny] == 0:
                if not visited[nx][ny]:
                    q.append((nx, ny))
                    visited[nx][ny] = True
                    cnt += 1

    return cnt
        
for x in range(M):
    for y in range(N):
        if paper[x][y] == 0 and not visited[x][y]:
            cnt = bfs(x, y)
            results.append(cnt) 

results.sort()

print(len(results))
for i in results:
    print(i, end=' ')