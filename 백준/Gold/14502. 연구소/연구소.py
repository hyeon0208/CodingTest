from collections import deque
import sys
import copy
input = sys.stdin.readline

n, m = map(int, input().split()) # 행, 열
virus_map = [list(map(int, input().split())) for _ in range(n)]
result = 0

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]

# 백트래킹으로 3개의 벽을 설치한모든 조합을 만듬
def createWall(wall_cnt):
    if wall_cnt == 3:
        bfs()
        return
    for x in range(n):
        for y in range(m):
            if virus_map[x][y] == 0:
                virus_map[x][y] = 1
                createWall(wall_cnt + 1)
                virus_map[x][y] = 0 

# 바이러스 전파
def bfs(): 
    global result
    wall_Arr = copy.deepcopy(virus_map)
    q = deque()

    for x in range(n): # 바이러스의 위치 정보 큐에 저장
        for y in range(m):
            if wall_Arr[x][y] == 2:
                q.append((x, y))

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < m and wall_Arr[nx][ny] == 0:
                wall_Arr[nx][ny] = 2
                q.append((nx, ny))

    #안전지대 개수 카운팅
    safezone = 0 
    for line in wall_Arr:
        safezone += line.count(0)
    result = max(safezone, result)

createWall(0)
print(result)