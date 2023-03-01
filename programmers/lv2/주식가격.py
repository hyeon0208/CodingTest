from collections import deque

def solution(prices):
    answer = [0] * len(prices)
    stack = []
    
    for cur_day, cur_price in enumerate(prices):
        while stack and stack[-1][1] > cur_price:
            prev_day, _ = stack.pop()
            answer[prev_day] = cur_day - prev_day
        stack.append((cur_day, cur_price))
        
    for i, _ in stack:
        answer[i] = len(prices) - 1 - i
    return answer
