def solution(arr):
    answer = [0, 0]
    
    def check(size, x, y):
        start = arr[x][y]
        for i in range(x, x + size):
            for j in range(y, y + size):
                if arr[i][j] != start:
                    half_size = size // 2
                    check(half_size, x, y)
                    check(half_size, x + half_size, y)
                    check(half_size, x, y + half_size)
                    check(half_size, x + half_size, y + half_size)
                    return
                
        answer[start] += 1
    check(len(arr), 0, 0)
    return answer