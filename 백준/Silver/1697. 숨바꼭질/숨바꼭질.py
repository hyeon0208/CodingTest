from collections import deque
import sys
input = sys.stdin.readline

n, k = map(int, input().split())

def bfs(n, k):
    visited = [-1] * 100001 
    visited[n] = 0
    q = deque([n])

    while q:
        cur = q.popleft()

        if cur == k:
            return visited[cur]

        for next in (cur + 1, cur - 1, cur * 2):
            if 0 <= next <= 100000 and visited[next] == -1:
                if next == cur * 2:
                    visited[next] = visited[cur] + 1
                    q.appendleft(next) # appendleft()로 순간이동을 먼저 탐색
                else:
                    visited[next] = visited[cur] + 1 # 걸어서 이동은 1초 소요
                    q.append(next)
print(bfs(n, k))