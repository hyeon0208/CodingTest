from collections import deque
import sys
input = sys.stdin.readline

def bfs(start):
    q = deque()
    q.append(start)
    visited[start] = True

    while q:
        cur = q.popleft()

        for next in graph[cur]:
            if not visited[next]:
                q.append(next)
                visited[next] = -visited[cur]
            elif visited[next] == visited[cur]:
                return False
    return True


    
K = int(input()) # 테스트 케이스의 개수

for _ in range(K):
    v, e = map(int, input().split())
    graph = [[] for _ in range(v + 1)]
    visited = [False] * (v + 1)

    for _ in range(e):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    for i in range(1, v + 1):
        if not visited[i]:
            result = bfs(i)
            if not result: # bfs 결과가 False면 (이분 그래프가 아니면)
                break

    print("YES" if result else "NO")