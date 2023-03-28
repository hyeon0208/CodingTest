from collections import deque

def solution(maps):
    answer = []
    row = len(maps)
    col = len(maps[0])
    visited = [[False] * col for _ in range(row)]
    
    def bfs(x, y):
        dx = [1, -1, 0, 0]
        dy = [0, 0, -1, 1]
        
        q = deque([(x, y)])
        visited[x][y] = True
        day = 0
        
        while q:
            curX, curY = q.popleft()
            day += int(maps[curX][curY])
            
            for i in range(4):
                nextX = curX + dx[i]
                nextY = curY + dy[i]
                if 0 <= nextX < row and 0 <= nextY < col and maps[nextX][nextY] != "X" and not visited[nextX][nextY]:
                    q.append((nextX, nextY))
                    visited[nextX][nextY] = True
        return day
                
    for x in range(row):
        for y in range(col):
            if maps[x][y] != "X" and not visited[x][y]:
                answer.append(bfs(x, y))
    
    if answer:
        return sorted(answer)
    else:
        return [-1]
    
    