from collections import deque
import sys
input = sys.stdin.readline

N, M = map(int, input().split()) # 행, 열
island = [list(map(int, input().split())) for _ in range(N)]
day = 0
result = False

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def bfs(x, y):
    q = deque()
    q.append((x, y))
    visited[x][y] = True

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < N and 0 <= ny < M:
                if island[nx][ny] != 0 and not visited[nx][ny]:
                    q.append((nx, ny))
                    visited[nx][ny] = True
                elif island[nx][ny] == 0:
                    sea[x][y] += 1

# 빙산이 두 덩이 이상으로 분리 될 때 까지 반복문
while True:
    visited = [[False] * M for _ in range(N)]
    sea = [[0] * M for _ in range(N)]
    iceberg = 0

    # 빙산 갯수 구하기
    for x in range(N):
        for y in range(M):
            if island[x][y] != 0 and not visited[x][y]:
                bfs(x, y)
                iceberg += 1

    # 차오른 바닷물 배열 sea로 빙산을 깍아줌
    for x in range(N):
        for y in range(M):
            island[x][y] -= sea[x][y]
            if island[x][y] < 0:
                island[x][y] = 0
                
    # 빙산이 2개 이상으로 분리되면 종료
    if iceberg >= 2:
        print(day)
        break
    if iceberg == 0: # 빙산이 분리되지 않으면 종료
        print(0)
        break
    
    day += 1
