from itertools import product

def solution(word):
    word_list = ["A", "E", "I", "O", "U"]
    
    a = []
    for i in range(1, 6):
        for j in product(word_list, repeat = i):
            a.append("".join(list(j)))
    a.sort()
    
    return a.index(word) + 1