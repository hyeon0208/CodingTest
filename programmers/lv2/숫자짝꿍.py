from collections import Counter

def solution(X, Y):
    arr = []
    gyo = Counter(X) & Counter(Y)
    
    if len(gyo) == 0:
        return "-1"
    
    for key, value in tuple(gyo.items()):
        while value != 0:
            arr.append(key)
            value -= 1
            
    answer = "".join(sorted(arr, reverse = True))
    
    if answer[0] == "0":
        answer = "0"
    
    return answer