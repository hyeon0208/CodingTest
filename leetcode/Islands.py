# -*- coding: utf-8 -*-

from collections import deque

def numIslands(grid):
    number_of_islands = 0
    m = len(grid)
    n = len(grid[0])
    visited = [[False] * n for _ in range(m)] # map과 같은 배열 생성.

    def bfs(x, y):
        # map에서 이동 방법 : 상하좌우 순
        dx = [-1, 1, 0, 0] 
        dy = [0, 0, -1, 1] 

        q = deque()
        visited[x][y] = True
        q.append((x, y))
        while q:
            cur_x, cur_y = q.popleft()
            for i in range(len(dx)):
                next_x = cur_x + dx[i]
                next_y = cur_y + dy[i]
                if next_x >= 0 and next_x < m and next_y >= 0 and next_y < n:
                    if grid[next_x][next_y] != "0" and not visited[next_x][next_y]:
                        visited[next_x][next_y] = True
                        q.append((next_x, next_y))

    for x in range(m):
        for y in range(n):
            if grid[x][y] == "1" and not visited[x][y]:
                bfs(x, y)
                number_of_islands += 1

    return number_of_islands

print(numIslands(grid=[
    ['1','1','0','0','0'],
    ['1','1','0','0','0'],
    ['1','1','0','0','0'],
    ['0','0','1','0','0'],
    ['0','0','0','1','1']
]))