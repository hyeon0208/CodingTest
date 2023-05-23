from collections import deque
import sys
input = sys.stdin.readline

n, m = map(int, input().split()) # 행, 열
maps = [list(map(int, input().rstrip())) for _ in range(n)]
visited = [[[0] * 2 for _ in range(m)] for _ in range(n)] 

dx = [-1,1,0,0]
dy = [0,0,-1,1]

def bfs(x, y, crush): 
    q = deque()
    q.append((x, y, crush))
    visited[x][y][crush] == 1

    while q:
        x, y, crush = q.popleft()

        if x == n - 1 and y == m - 1:
            return visited[x][y][crush] + 1

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < m and visited[nx][ny][crush] == 0:
                # 이동할 수 있는 경우
                if maps[nx][ny] == 0: 
                    q.append((nx, ny, crush))
                    visited[nx][ny][crush] = visited[x][y][crush] + 1
                # 이동할 수 없는 경우 벽을 부시고 이동
                if maps[nx][ny] == 1 and crush == 1:
                    q.append((nx, ny, crush - 1))
                    visited[nx][ny][crush - 1] = visited[x][y][crush] + 1
    return -1


print(bfs(0, 0, 1))
