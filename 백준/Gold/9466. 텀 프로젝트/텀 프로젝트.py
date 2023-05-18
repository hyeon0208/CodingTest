import sys
sys.setrecursionlimit(10 ** 8)
input = sys.stdin.readline

def dfs(start):
    global cnt
    visited[start] = True
    route.append(start)

    next = graph[start]
    if visited[next]: # 사이클이 되는 팀을 뺌
        if next in route:
            cnt -= len(route[route.index(next):])
        return
    else: # 방문하지 않았다면 다음을 탐색
        dfs(next)
            
T = int(input())

for _ in range(T):
    n = int(input())

    graph = [0] + list(map(int, input().split()))

    visited = [False] * (n + 1)
    cnt = n

    for i in range(1, n + 1): # 1번 학생부터 탐색 시작
        if not visited[i]:
            route = []
            dfs(i)

    print(cnt)