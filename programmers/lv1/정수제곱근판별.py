import math

def solution(n):
    x = math.sqrt(n)
    
    if int(x) ** 2 == n:
        return (x + 1) ** 2
    
    return -1