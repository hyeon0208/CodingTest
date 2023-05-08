n=int(input()) # 컴퓨터 개수
v=int(input()) # 연결선 개수

graph = [[] for _ in range(n + 1)] # 그래프 초기화
visited=[False] * (n + 1) # 방문한 컴퓨터인지 표시

for i in range(v): # 그래프 생성
    a,b=map(int,input().split())
    graph[a].append(b) 
    graph[b].append(a) 

def dfs(start):
    visited[start]=True
    for next in graph[start]:
        if not visited[next]:
            dfs(next)
dfs(1)
print(sum(visited)-1)