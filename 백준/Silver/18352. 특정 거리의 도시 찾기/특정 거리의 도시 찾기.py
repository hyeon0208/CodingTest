from collections import deque
import sys
input = sys.stdin.readline

n, m, k, x = map(int, input().split()) # 도시 개수, 도로 개수, 거리 정보, 출발 도시의 번호
cities = [[] for _ in range(n + 1)]
visited = [-1] * (n + 1)
result = []

for _ in range(m):
    a, b = map(int, input().split())
    cities[a].append(b)

def bfs(start):
    q = deque()
    q.append(start)
    visited[start] = 0

    while q:
        cur = q.popleft()

        if visited[cur] == k:
            result.append(cur)

        for next in cities[cur]:
            if visited[next] == -1:
                q.append(next)
                visited[next] = visited[cur] + 1

bfs(x)

if not result:
    print(-1)
    exit(0)
else:
    result.sort()
    for n in result:
        print(n)