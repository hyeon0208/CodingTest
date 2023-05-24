from collections import deque
import sys
input = sys.stdin.readline

n = int(input())
sea = [list(map(int, input().split())) for _ in range(n)]
shark_size = 2


dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def bfs(x, y, shark_size): 
    visited = [[False] * n for _ in range(n)]
    distance = [[False] * n for _ in range(n)]
    eats = []

    q = deque()
    q.append((x, y))
    visited[x][y] = True
    

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
                if sea[nx][ny] <= shark_size: # 다음으로 이동이 가능할 경우
                    q.append((nx, ny))
                    visited[nx][ny] = True
                    distance[nx][ny] = distance[x][y] + 1
                    if sea[nx][ny] < shark_size and sea[nx][ny] != 0: # 물고기가 있고 그걸 먹을 수 있다면
                        eats.append((nx, ny, distance[nx][ny]))
    return sorted(eats, key=lambda x : ((x[2], x[0], x[1]))) # 거리, x, y 순으로 오름차순

time = 0
eat_cnt = 0

# 아기상어 위치 좌표 
for i in range(n):
    for j in range(n):
        if sea[i][j] == 9:
            x, y = i, j

while True:
    shark = bfs(x, y, shark_size)
    # 먹을 물고기가 없다면 엄마에게 도움 요청
    if not shark:
        break

    nx, ny, d = shark[0]  # 가장 거리 가까운 물고기를 꺼내 먹는다.
    time += d
    eat_cnt += 1

    # 먹은 물고기 수와 크기가 같다면 크기 1 증가
    if shark_size == eat_cnt:
        shark_size += 1
        eat_cnt = 0

    # 상어좌표를 먹은 물고기 좌표로 옮겨준다.
    sea[x][y] = 0
    x, y = nx, ny

print(time)