from collections import deque
import sys
input = sys.stdin.readline

m, n, h = map(int, input().split()) # 열, 행, 상자수
boxes = [[list(map(int, input().split())) for _ in range(n)] for _ in range(h)]
answer = 0
q = deque()

dx = [-1,1,0,0,0,0]
dy = [0,0,-1,1,0,0]
dz = [0,0,0,0,-1,1]

def bfs(): 
    while q:
        box, x, y = q.popleft()

        for i in range(6):
            nx = x + dx[i]
            ny = y + dy[i]
            nbox = box + dz[i]

            if 0 <= nbox < h and 0 <= nx < n and 0 <= ny < m:
                if boxes[nbox][nx][ny] == 0:
                    q.append((nbox, nx, ny))
                    boxes[nbox][nx][ny] = boxes[box][x][y] + 1

for box in range(h):
    for x in range(n):
        for y in range(m):
            if boxes[box][x][y] == 1:
                q.append((box, x, y))

bfs()

for box in boxes:
    for x in box:
        for y in x:
            if y == 0:
                print(-1)
                exit(0)            
        answer = max(answer, max(x))

print(answer-1)
