from collections import deque
import sys
input = sys.stdin.readline

n, m = map(int, input().split()) # 행, 열
board = [list(input().rstrip()) for _ in range(n)]
visited = []

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]
cnt = 0

def getPos():
    rx, ry, bx, by = 0, 0, 0, 0
    for x in range(n):
        for y in range(m):
            if board[x][y] == "R":
                rx, ry = x, y
            if board[x][y] == "B":
                bx, by = x, y
    return rx, ry, bx, by


def move(x, y, dx, dy):
    cnt = 0
    while board[x+dx][y+dy] != '#' and board[x][y] != 'O':
        x += dx
        y += dy
        cnt +=1
    return x, y, cnt
    
def bfs():
    rx, ry, bx, by = getPos()

    q = deque()
    q.append((rx, ry, bx, by, 1))
    visited.append((rx, ry, bx, by))

    while q:
        rx, ry, bx, by, depth = q.popleft()

        if depth > 10:
            break

        for i in range(4):
            nrx, nry, rcnt = move(rx, ry, dx[i], dy[i])
            nbx, nby, bcnt = move(bx, by, dx[i], dy[i])

            # 파란 구슬이 구멍에 들어갈 경우
            if board[nbx][nby] == "O":
                continue

            # 빨간 구슬이 들어갈 경우 성공
            if board[nrx][nry] == "O":
                print(depth)
                return

            # 둘이 겹쳐있을경우 더 많이 이동한녀석을 1칸 뒤로 물려줌
            if nrx==nbx and nry==nby:
                if rcnt > bcnt:
                    nrx -= dx[i]
                    nry -= dy[i]
                else:
                    nbx -= dx[i]
                    nby -= dy[i]
            if (nrx, nry, nbx, nby) not in visited:
                visited.append((nrx, nry, nbx, nby))
                q.append((nrx,nry,nbx,nby, depth+1))
    print(-1)

bfs()