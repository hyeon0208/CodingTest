from collections import deque
import sys
input = sys.stdin.readline

N = int(input())

graph = [[] for i in range(N + 1)] 
result = [0] * (N + 1)

for i in range(N - 1):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def bfs():
    q = deque()
    q.append(1)

    while q:
        cur = q.popleft()
        for next in graph[cur]:
            if result[next] == 0:
                result[next] = cur
                q.append(next)

bfs()

for i in result[2:]:
    print(i)

