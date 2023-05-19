import sys
sys.setrecursionlimit(10 ** 8)
input = sys.stdin.readline

n = int(input())
forest = [list(map(int, input().split())) for _ in range(n)]

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]

def dfs(x, y):
    if dp[x][y] != 0: # 이미 한번 지난 경로일 경우 해당 지점의 dp값부터 시작
        return dp[x][y]
    dp[x][y] = 1

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < n and 0 <= ny < n and forest[nx][ny] > forest[x][y]:
            dp[x][y] = max(dp[x][y], dfs(nx, ny) + 1)
    return dp[x][y]

cnt = 1
dp = [[0] * n for _ in range(n)]

for x in range(n):
    for y in range(n):
        if dp[x][y] == 0:
             cnt = max(cnt, dfs(x, y))

print(cnt)