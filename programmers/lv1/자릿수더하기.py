def solution(n):
    answer = 0
    new = str(n)
    for num in range(len(new)):
        answer += int(new[num])
    return answer
    
print(solution(123))