from collections import deque

def bfs(start, end, maps):
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]
    
    row = len(maps)
    col = len(maps[0])
    
    visited = [[False] * col for _ in range(row)]
    
    q = deque()
    
    for i in range(row):
        for j in range(col):
            if maps[i][j] == start:
                q.append((i, j, 0))
                visited[i][j] = True
                break
                        
    while q:
        x, y, distance = q.popleft()
        
        if maps[x][y] == end:
            return distance
        
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if 0 <= nx < row and 0 <= ny < col and maps[nx][ny] != "X" and not visited[nx][ny]:
                q.append((nx, ny, distance + 1))
                visited[nx][ny] = True
    return -1

def solution(maps):
    path1 = bfs('S', 'L', maps)	# 시작 지점 --> 레버
    path2 = bfs('L', 'E', maps) # 레버 --> 출구
    
    if path1 != -1 and path2 != -1:
        return path1 + path2
        
    return -1