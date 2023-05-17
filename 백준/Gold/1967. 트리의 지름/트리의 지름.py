import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

n = int(input())

tree = [[] for _ in range(n+1)]

for _ in range(n-1):
    p, c, d = map(int, input().split())
    tree[p].append((c, d))
    tree[c].append((p, d))

def dfs(start, distance):
    for next, next_d in tree[start]:
        if visited[next] == -1:
            visited[next] = distance + next_d
            dfs(next, distance + next_d)

# 시작 정점에서 임의의 정점까지의 거리를 구하고 그 중 가장 먼 거리를 구한다.
visited = [-1] * (n+1)
visited[1] = 0
dfs(1, 0)

# 위에서 찾은 노드에 대한 가장 먼 노드를 찾는다.
# 가장 먼 노드를 시작지점으로 하여 다시 한번 가장 긴 거리를 찾는다.
last_Node = visited.index(max(visited))
visited = [-1] * (n + 1)
visited[last_Node] = 0
dfs(last_Node, 0)

print(max(visited))
