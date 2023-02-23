# -*- coding: utf-8 -*-

from collections import deque

def shortestPathBinaryMatrix(grid):
    shortest_path_len = -1
    row = len(grid)
    col = len(grid[0])

    if grid[0][0] == 1 or grid[row - 1][col - 1] == 1:
        return shortest_path_len

    # 8방향 이동 : 아래, 위, 오른쪽, 왼쪽, ↗, ↖, ↘, ↙
    direction = [(1, 0), (-1, 0), (0, 1), (0, -1),
                 (1, 1), (1, -1), (-1, 1), (-1, -1)]

    visited = [[False] * col for _ in range(row)]
    q = deque() # 행, 열, 길이
    q.append((0, 0, 1))
    visited[0][0] = True

    while q:
        cur_row, cur_col, cur_len = q.popleft()
        # 목적지에 도착했을 때 최단 거리를 저장한다.
        if cur_row == row - 1 and cur_col == col - 1:
            shortest_path_len = cur_len
            break

        for move_row, move_col in direction:
            next_row = cur_row + move_row
            next_col = cur_col + move_col
            if next_row >= 0 and next_row < row and next_col >= 0 and next_col < col:
                if grid[next_row][next_col] == 0 and not visited[next_row][next_col]:
                    q.append((next_row, next_col, cur_len + 1))
                    visited[next_row][next_col] = True
    return shortest_path_len

print(shortestPathBinaryMatrix(grid = [[0,0,0],[1,1,0],[1,1,0]]))