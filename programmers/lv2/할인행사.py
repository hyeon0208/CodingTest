from collections import Counter

def solution(want, number, discount):
    answer = 0
    dict = {}

    for w, n in zip(want, number):
        dict[w] = n
        
    for i in range(len(discount) - 9):
        if dict == Counter(discount[i:i+10]):            
            answer += 1
    
    return answer