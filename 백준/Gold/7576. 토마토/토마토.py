from collections import deque
import sys
input = sys.stdin.readline

m, n = map(int, input().split()) # 열, 행
field = [list(map(int, input().split())) for _ in range(n)]
q = deque()
result = 0

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]

zero_cnt = 0
for i in field:
    if 0 in i:
        zero_cnt += 1

# 이미 모든 토마토가 익어 있다면 0을 출력하고 종료
if zero_cnt == 0:
    print(0)
    exit(0)

def bfs():
    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < m and field[nx][ny] == 0:
                q.append((nx, ny))
                field[nx][ny] = field[x][y] + 1

# 시작지점이 2개가 있을 때는 시작점을 서로 번갈아 가며 출발해야하므로 시작점을 먼저 q에 추가.
for x in range(n):
    for y in range(m):
        if field[x][y] == 1:
            q.append((x, y))

bfs()

for i in field:
    if 0 in i:
        print(-1)
        exit(0)
    result = max(result, max(i))
print(result - 1)
