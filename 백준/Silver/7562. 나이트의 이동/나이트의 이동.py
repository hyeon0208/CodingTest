from collections import deque
import sys
input = sys.stdin.readline

dx = [1, 1, 2, 2, -1, -1, -2, -2]
dy = [-2, 2, -1, 1, -2, 2, -1, 1]

def bfs(x, y): 
    q = deque()
    q.append((x, y))

    while q:
        x, y = q.popleft()

        if x == ex and y == ey:
            return chessmap[x][y]

        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < l and 0 <= ny < l and chessmap[nx][ny] == 0:
                q.append((nx, ny))
                chessmap[nx][ny] = chessmap[x][y] + 1


for _ in range(int(input())): # 테스트 케이스
    l = int(input()) # 한 변의 길이
    chessmap = [[0] * l for _ in range(l)]
    sx, sy = map(int, input().split()) # 시작지점 행, 열
    ex, ey = map(int, input().split()) # 도착지점 행, 열
    print(bfs(sx, sy))