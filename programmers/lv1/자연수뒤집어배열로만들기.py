def solution(n):
    answer = list(str(n))
    answer.reverse()
    return list(map(int, answer))

print(solution(12345))
