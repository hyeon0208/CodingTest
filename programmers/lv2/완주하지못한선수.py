from collections import Counter

def solution(participant, completion):
    gyo = Counter(participant) - Counter(completion)
    return list(gyo.keys())[0]