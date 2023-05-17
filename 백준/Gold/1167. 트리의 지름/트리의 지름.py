import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

V = int(input())
graph = [[] for _ in range(V + 1)]

for _ in range(V):
    info = list(map(int, input().split()))
    for n in range(1, len(info) - 2, 2):
        graph[info[0]].append((info[n], info[n + 1])) # (연결노드, 거리)

def dfs(start, distance):
    for next, next_d in graph[start]:
        if visited[next] == -1:
            visited[next] = distance + next_d
            dfs(next, distance + next_d)

visited = [-1] * (V + 1)
visited[1] = 0
dfs(1, 0)

last_Node = visited.index(max(visited))
visited = [-1] * (V + 1)
visited[last_Node] = 0
dfs(last_Node, 0)

print(max(visited))