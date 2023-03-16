def solution(survey, choices):
    answer = ''
    dict = {"R" : 0, "T" : 0, "C" : 0, "F" : 0, "J" : 0, "M" : 0, "A" : 0, "N" : 0}
    scoreTable = [0, 3, 2, 1, 0, 1, 2, 3]
    
    for s, c in zip(survey, choices):
        if c < 4: # 비동의
            dict[s[0]] += scoreTable[c]
        elif c > 4: # 동의
            dict[s[1]] += scoreTable[c]

    li = list(dict.items())
    
    for i in range(0, len(li), 2):
        if li[i][1] > li[i + 1][1]:
            answer += li[i][0]
        elif li[i][1] < li[i + 1][1]:
            answer += li[i + 1][0]
        else:
            temp = li[i][0] + li[i + 1][0]
            answer += sorted(temp)[0]
    
    return answer