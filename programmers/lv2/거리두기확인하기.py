from collections import deque

def solution(places):
    answer = []
    
    def bfs(place):
        start = []
        for i in range(5):
            for j in range(5):
                if place[i][j] == "P":
                    start.append([i, j])
                
        for s in start:
            q = deque()
            visited = [[False] * 5 for _ in range(5)]
            distance = [[0] * 5 for _ in range(5)]
            q.append(s)
            visited[s[0]][s[1]] = True
        
            while q:
                dx = [1, -1, 0, 0]
                dy = [0, 0, 1, -1]

                curX, curY = q.popleft()
                for i in range(4):
                    nextX = curX + dx[i]
                    nextY = curY + dy[i]

                    if 0 <= nextX < 5 and 0 <= nextY < 5 and not visited[nextX][nextY]:
                        if place[nextX][nextY] == "O":
                            q.append((nextX, nextY))
                            visited[nextX][nextY] = True
                            distance[nextX][nextY] = distance[curX][curY] + 1
                        if place[nextX][nextY] == "P" and distance[curX][curY] + 1 <= 2:
                            return 0
        return 1
            
    for place in places:
        answer.append(bfs(place))
        
    return answer