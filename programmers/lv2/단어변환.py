from collections import deque

def solution(begin, target, words):
    if target not in words:
        return 0

    q = deque()
    q.append((begin, 0))
    
    while q:
        w, cnt = q.popleft()
        
        if w == target:
            return cnt
                
        for i in range(0, len(words)):
            diff = 0
            for j in range(len(w)):
                if w[j] != words[i][j]:
                    diff += 1
            if diff == 1:
                q.append((words[i], cnt + 1))
                
    return 0