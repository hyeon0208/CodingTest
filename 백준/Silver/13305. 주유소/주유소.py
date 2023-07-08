import sys
input = sys.stdin.readline

n = int(input())
distances = list(map(int, input().split()))
oilCosts = list(map(int, input().split()))

total = distances[0] * oilCosts[0]
minCost = oilCosts[0] # 가장 값이 싼 주유소

for cur in range(1, n - 1):
    # 가장 값이 싼 주유소가 현재 주유소보다 비쌀 경우 현재 주유소를 가장 싼 주유소로 바꿔준다.
    if minCost > oilCosts[cur]: 
        minCost = oilCosts[cur]
    total += minCost * distances[cur]

print(total)
