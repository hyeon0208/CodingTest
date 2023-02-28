from collections import deque

def solution(priorities, location):
    answer = 0
    
    q = deque([(k, v) for k, v in enumerate(priorities)])
    
    while q:
        cur_doc, cur_p = q.popleft()
        if q and cur_p < max(q, key = lambda x : x[1])[1]:
            q.append((cur_doc, cur_p))
        else:
            answer += 1
            if cur_doc == location:
                break
    return answer