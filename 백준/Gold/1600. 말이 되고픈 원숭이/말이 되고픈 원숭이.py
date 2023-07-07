from collections import deque
import sys
input = sys.stdin.readline

k = int(input())
w, h = map(int, input().split()) # 열, 행

zoo = [list(map(int, input().split())) for _ in range(h)]

# 상하좌우, 체스 나이트 이동
dist = [[1,0],[0,1],[-1,0],[0,-1]]
horse = [[-2,-1], [-2,1],[-1,-2],[-1,2],[2,-1],[2,1],[1,-2],[1,2]]

def bfs():
  visited = [[[0] * (k + 1) for _ in range(w)] for _ in range(h)]
  q = deque()
  q.append([0,0,0])
  visited[0][0][0] = 1

  while q:
    x, y, z = q.popleft()

    # 목표 지점 도달
    if x== h - 1 and y== w - 1:
      return visited[x][y][z] - 1

    # 상하좌우로 이동
    for i,j in dist:
      dx, dy = x+i, y+j
      if 0 <= dx < h and 0 <= dy< w and not visited[dx][dy][z] and not zoo[dx][dy]:
        visited[dx][dy][z] = visited[x][y][z] + 1
        q.append((dx ,dy ,z))

    # 말 이동 수보다 작을 경우에만 이동
    if z < k:
      for hi, hj in horse:
        hx, hy = x + hi, y + hj
        if 0 <= hx < h and 0 <= hy < w:
          if not zoo[hx][hy]:
            if not visited[hx][hy][z+1]:
              visited[hx][hy][z+1] = visited[x][y][z]+1
              q.append((hx, hy, z+1))
    
  return -1

print(bfs())