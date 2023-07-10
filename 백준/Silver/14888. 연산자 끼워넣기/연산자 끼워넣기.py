import sys
input = sys.stdin.readline

n = int(input())
nums = list(map(int, input().split()))
signs = list(map(int, input().split()))

maxNum = -1e9
minNum = 1e9

def dfs(depth, total, plus, minus, multiply, divide):
    global maxNum, minNum
    if depth == n:
        maxNum = max(total, maxNum)
        minNum = min(total, minNum)
        return
    
    if plus:
        dfs(depth + 1, total + nums[depth], plus - 1, minus, multiply, divide)
    if minus:
        dfs(depth + 1, total - nums[depth], plus, minus - 1, multiply, divide)
    if multiply:
        dfs(depth + 1, total * nums[depth], plus, minus, multiply - 1, divide)
    if divide:
        dfs(depth + 1, int(total / nums[depth]), plus, minus, multiply, divide - 1)

dfs(1, nums[0], signs[0], signs[1], signs[2], signs[3])

print(maxNum)
print(minNum)