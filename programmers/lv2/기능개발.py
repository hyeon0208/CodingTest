import math

def solution(progresses, speeds):
    answer = []
    days = [math.ceil((100 - a) / b) for a, b in zip(progresses, speeds)]
    
    now = 0
    cnt = 1
    for next in range(1, len(days)):
        if days[now] >= days[next]:
            cnt += 1
        else:
            now = next
            answer.append(cnt)
            cnt = 1
    answer.append(len(days) - now)
    return answer