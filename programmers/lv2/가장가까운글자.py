def solution(s):
    answer = []
    dict = {}

    for i, c in enumerate(s):
        if c not in dict:
            dict[c] = i
            answer.append(-1)
        else:
            answer.append(i - dict[c])
            dict[c] = i
    
    return answer