from collections import deque
import sys
input = sys.stdin.readline

n, k = map(int, input().split()) # 수빈이의 위치, 동생의 위치

def bfs():
    visited = [-1] * 100001
    visited[n] = 0
    q = deque([n])

    while q:
        cur = q.popleft()

        # 동생의 위치에 도달하면 값 반환
        if cur == k:
            return visited[cur]

        # 동생의 위치에 도달할 때 까지, 3가지의 이동 경우를 확인 (x + 1, x - 1, x * 2)
        for next in (cur + 1, cur - 1, cur * 2):
            if 0 <= next <= 100000 and visited[next] == -1:
                if next == cur * 2:
                    visited[next] = visited[cur] # 순간이동은 0초 소요
                    q.appendleft(next)
                else:
                    visited[next] = visited[cur] + 1 # 걸어서 이동은 1초 소요
                    q.append(next)

print(bfs())