from collections import deque
import sys
sys.setrecursionlimit(300000)
input = sys.stdin.readline

n = int(input())
graph = [[] for _ in range(n+1)]
s, e = map(int, input().split())
m = int(input())

for _ in range(m):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)

relationship = [0] * (n+1)

def dfs(node):
    for i in graph[node]:
        if relationship[i] == 0:
            relationship[i] = relationship[node] + 1
            dfs(i)

dfs(s)

print(relationship[e] if relationship[e] > 0 else -1)
