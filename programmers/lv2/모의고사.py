def solution(answers):
    answer = []
    score = [0] * 3
    
    st1 = [1,2,3,4,5]
    st2 = [2,1,2,3,2,4,2,5]
    st3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    
    for i in range(len(answers)):
        if st1[i % len(st1)] == answers[i]:
            score[0] += 1
        if st2[i % len(st2)] == answers[i]:
            score[1] += 1
        if st3[i % len(st3)] == answers[i]:
            score[2] += 1
                
    max_score = max(score)
    
    for i in range(len(score)):
        if score[i] != max_score:
            continue
        else:
            answer.append(i + 1)
        
    return answer