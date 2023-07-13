from collections import deque
import sys
input = sys.stdin.readline
from itertools import combinations

N, M = map(int, input().split()) # 연구소의 크기, 바이러스의 개수

laboratory = [list(map(int, input().split())) for _ in range(N)]
dx = [0, -1, 0, 1]
dy = [-1, 0, 1, 0]

def bfs(virus, blank):
    q = deque()
    visited = [[-1] * N for _ in range(N)]
    time = 0

    for i, j in virus:
        q.append((i, j))
        visited[i][j] = 0

    while q:
        x, y = q.popleft()
        
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < N and 0 <= ny < N:
                # 빈 칸을 만난 경우
                if laboratory[nx][ny] == 0 and visited[nx][ny] == -1:
                    q.append((nx, ny))
                    visited[nx][ny] = visited[x][y] + 1
                    blank -= 1
                    time = max(time, visited[nx][ny])
                # 비활성화 바이러스를 만난 경우
                if laboratory[nx][ny] == 2 and visited[nx][ny] == -1:
                    q.append((nx, ny))
                    visited[nx][ny] = visited[x][y] + 1
    
    if blank != 0:
        return 1e9
    else:
        return time

blank = 0
virus_pos = []
for x in range(N):
    for y in range(N):
        if laboratory[x][y] == 2:
            virus_pos.append((x, y))
        if laboratory[x][y] == 0:
            blank += 1

result = 1e9
for virus in combinations(virus_pos, M):
    result = min(result, bfs(virus, blank))

if result != 1e9:
    print(result)
else:
    print(-1)
