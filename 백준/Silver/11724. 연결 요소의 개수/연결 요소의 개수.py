import sys

from collections import deque

n, m = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(n+1)]
visited = [False for _ in range(n+1)]
result = 0

def bfs(x):
    que = deque([x])
    visited[x] = True
    while que:
        now = que.popleft()
        for _next in graph[now]:
            if not visited[_next]:
                que.append(_next)
                visited[_next] = True
    return 1

for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

for i in range(1, n+1):
    if not visited[i]:
        result += bfs(i)
        
sys.stdout.write(str(result))