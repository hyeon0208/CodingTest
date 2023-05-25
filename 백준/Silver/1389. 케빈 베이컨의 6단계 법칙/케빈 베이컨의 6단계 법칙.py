from collections import deque
import sys
input = sys.stdin.readline

n, m = map(int, input().split()) # 유저 수, 친구관계 수

relationship = [[] for _ in range(n + 1)]

for i in range(m):
    a, b = map(int, input().split())
    relationship[a].append(b)
    relationship[b].append(a)

def bfs(start):
    distance = [0] * (n + 1)
    visited = [False] * (n + 1)
    q = deque()
    q.append(start)
    visited[start] = True
    
    while q:
        cur = q.popleft()

        for next in relationship[cur]:
            if not visited[next]:
                distance[next] = distance[cur] + 1
                visited[next] = True
                q.append(next)

    return sum(distance)

cnt = []
for start in range(1, n + 1):
    cnt.append(bfs(start))

print(cnt.index(min(cnt)) + 1)