def solution(clothes):
    dict = {}
    answer = 1
    
    for c in clothes:
        if c[1] in dict:
            dict[c[1]] += 1
        else:
            dict[c[1]] = 1

    for i in dict.values():
        answer *= i + 1
    return answer - 1
