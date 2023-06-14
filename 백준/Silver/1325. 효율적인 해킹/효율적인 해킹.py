from collections import deque
import sys
input = sys.stdin.readline

n, m = map(int, input().split())
relationship = [[] for _ in range(n + 1)]

# A가 B를 신뢰한다. (노드로 표현하면 B는 A의 부모이다.)
for _ in range(m):
    A, B = map(int, input().split())
    relationship[B].append(A)

def bfs(start):
    q = deque()
    q.append(start)
    cnt = 0

    visited = [False] * (n + 1)
    visited[start] = True

    while q:
        cur = q.popleft()
        for next in relationship[cur]:
            if not visited[next]:
                visited[next] = True
                q.append(next)
                cnt += 1
    return cnt

result = []
for start in range(1, len(relationship)):
    result.append(bfs(start))

for i in range(len(result)):
    if max(result) == result[i]:
        print(i + 1)