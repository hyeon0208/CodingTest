def solution(rows, columns, queries):
    answer = []
    map = [[0] * columns for _ in range(rows)]
    
    n = 1
    for x in range(rows):
        for y in range(columns):
            map[x][y] = n
            n += 1
                
    for x1, y1, x2, y2 in queries:
        start = map[x1 - 1][y1 -1]
        min_num = start
        
        # 왼쪽
        for i in range(x1 - 1, x2 - 1):
            temp = map[i + 1][y1 - 1]
            map[i][y1 - 1] = temp
            min_num = min(min_num, temp)
        
        # 아래
        for i in range(y1 - 1, y2 - 1):
            temp = map[x2 - 1][i + 1]
            map[x2 - 1][i] = temp
            min_num = min(min_num, temp)

        # 오른쪽
        for i in range(x2 - 1, x1 - 1, -1):
            temp = map[i - 1][y2 - 1]
            map[i][y2 - 1] = temp
            min_num = min(min_num, temp)
        
        # 위
        for i in range(y2 - 1, y1 - 1, -1):
            temp = map[x1 - 1][i - 1]
            map[x1 - 1][i] = temp
            min_num = min(min_num, temp)
            
        map[x1 - 1][y1] = min_num
        answer.append(min_num)
    
    return answer