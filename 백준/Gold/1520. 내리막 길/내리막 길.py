from collections import deque
import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

M, N = map(int, input().split()) # 행, 열

numberMap = [list(map(int, input().split())) for _ in range(M)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

dp = [[-1] * N for _ in range(M)] 

def dfs(x, y):
    # 목적지에 도착했을 경우
    if x == M - 1 and y == N - 1:
        return 1

    # 이미 방문한 적이 있을 경우
    if dp[x][y] != -1:
        return dp[x][y]

    dp[x][y] = 0

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0 <= nx < M and 0 <= ny < N and numberMap[x][y] > numberMap[nx][ny]:
            dp[x][y] += dfs(nx, ny)
            
    return dp[x][y]

print(dfs(0, 0))