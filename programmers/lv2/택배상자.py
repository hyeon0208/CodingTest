def solution(order):
    answer = 0
    sub = []
    
    i = 1
    while i < len(order) + 1:
        sub.append(i)
        while sub and sub[-1] == order[answer]:
            answer += 1
            sub.pop()
        i += 1
    
    return answer