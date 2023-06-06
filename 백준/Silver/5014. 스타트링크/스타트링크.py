from collections import deque
import sys
input = sys.stdin.readline

f, s, g, u, d = map(int, input().split()) # 층수, 강호, 스타트링크, 위, 아래
visited = [-1] * (f + 1)

def bfs(start):
    q = deque()
    q.append(start)
    visited[start] = 0

    while q:
        cur = q.popleft()

        if cur == g:
            return visited[cur]

        for next in (cur + u, cur - d):
            if 0 < next <= f and visited[next] == -1: # 건물은 1층 부터 시작
                visited[next] = visited[cur] + 1
                q.append(next)
        
    if visited[g] == -1:
        return "use the stairs"

print(bfs(s))