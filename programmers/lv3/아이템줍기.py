from collections import deque
def solution(rectangle, characterX, characterY, itemX, itemY):
    answer = 0
    
    # 제한사항에서 모든 좌표값은 1 이상 50 이하라고 했고 2배의 좌표를 그려야 하므로 102*102 크기의 2차원 배열 선언
    field = [[5] * 102 for i in range(102)]
    
    # 직사각형 그리기
    for r in rectangle:
    	# 모든 좌표값 2배 (좀 더 경로 계산이 쉽게 직사각형을 크게 그림)
        x1, y1, x2, y2 = map(lambda x: x*2, r)
        # x1부터 x2, y1부터 y2까지 순회
        for i in range(x1, x2+1):
            for j in range(y1, y2+1):
            	# x1, x2, y1, y2는 테두리이므로 제외하고 내부만 0으로 채움
                if x1 < i < x2 and y1 < j < y2:
                    field[i][j] = 0
                # 다른 직사각형의 테두리일 때, 그 직사각형의 테두리를 0으로 채움
                elif field[i][j] != 0:
                    field[i][j] = 1
    
    # BFS()
    q = deque()
    q.append([characterX * 2, characterY * 2])
    visited = [[False] * 102 for i in range(102)]
    visited[characterX * 2][characterY * 2] = True
    
    directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    
    while q:
        x, y = q.popleft()
        if x == itemX * 2 and y == itemY * 2:
            return field[x][y] // 2
        
        for dx, dy in directions:
            nextX = x + dx
            nextY = y + dy
            if not visited[nextX][nextY] and field[nextX][nextY] == 1:
                q.append([nextX, nextY])
                field[nextX][nextY] = field[x][y] + 1

    return answer