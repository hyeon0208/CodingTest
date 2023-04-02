def solution(storey):
    answer = []
    
    def dfs(storey, count):
        if storey == 0:
            answer.append(count)
            return
        
        up = 10 - storey % 10
        down = storey % 10
        if up < down:
            dfs(storey // 10 + 1, count + up)
        elif down < up:
            dfs(storey // 10, count + down)
        else:
            for i in range(2):
                dfs(storey // 10 + i, count + up)
    
    dfs(storey, 0)
    return min(answer)