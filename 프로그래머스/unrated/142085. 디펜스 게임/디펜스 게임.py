import heapq as hq

def solution(n, k, enemy):
    
    if k == len(enemy):
        return k

    answer = 0
    sumE = 0
        
    heap = []
    
    for e in enemy:
        hq.heappush(heap, -e)
        sumE += e
        if sumE > n:
            if k == 0: 
                break
            sumE += hq.heappop(heap) 
            k -= 1
        answer += 1
    
    return answer