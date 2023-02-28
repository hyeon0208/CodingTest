answer = 0

def solution(numbers, target):
    def dfs(depth, sum):
        global answer
        if depth == len(numbers):
            if sum == target:
                answer += 1
            return
        dfs(depth + 1, sum + numbers[depth])
        dfs(depth + 1, sum - numbers[depth])

    dfs(0, 0)
        
    return answer