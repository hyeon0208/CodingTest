from collections import deque
import sys
input = sys.stdin.readline

n, k = map(int, input().split())
visited = [-1] * 100001

def bfs(start):
    q = deque()
    q.append(start)
    visited[start] = 0

    find_sec = sys.maxsize
    cnt = 0

    while q:
        cur = q.popleft()

        if cur == k:
            find_sec = min(find_sec, visited[cur])
            cnt += 1
            
        for next in (cur + 1, cur - 1, cur * 2):
            if 0 <= next < 100001 and (visited[next] == -1 or visited[next] == visited[cur] + 1):
                visited[next] = visited[cur] + 1
                if next == cur * 2:
                    q.appendleft(next)
                else:
                    q.append(next)

    return find_sec, cnt

result = bfs(n)
print(result[0])
print(result[1])