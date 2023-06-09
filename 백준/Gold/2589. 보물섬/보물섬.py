from collections import deque
import sys
input = sys.stdin.readline

r, c = map(int, input().split())
board = [list(input().rstrip()) for _ in range(r)]

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x, y):
    q = deque()
    q.append((x, y))
    visited = [[-1] * c for _ in range(r)]
    visited[x][y] = 0
    cnt = 0

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < r and 0 <= ny < c and visited[nx][ny] == -1:
                if board[nx][ny] == "L":
                    q.append((nx, ny))
                    visited[nx][ny] = visited[x][y] + 1
                    cnt = max(cnt, visited[nx][ny])
    return cnt

result = 0
for x in range(r):
    for y in range(c):
        if board[x][y] == "L":
            result = max(result, bfs(x, y))

print(result)