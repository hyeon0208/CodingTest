def solution(k, m, score):
    answer = 0
    score.sort(reverse = True)
    boxs = []
    for i in range(0, len(score), m):
        boxs.append(score[i:i + m])
    
    for box in boxs:
        if len(box) == m:
            answer += min(box) * m
        
    return answer