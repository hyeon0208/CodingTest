from itertools import combinations
import math

def solution(nums):
    answer = 0
    
    combi = combinations(nums, 3)
    for arr in combi:
        num = sum(arr)
        if isPrime(num):
            answer += 1
    return answer

def isPrime(num):
    for i in range(2, int(math.sqrt(num)) + 1):
        if num % i == 0:
            return False
    return True
    