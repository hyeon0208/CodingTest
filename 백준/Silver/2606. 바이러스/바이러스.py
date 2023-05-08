n=int(input()) # 컴퓨터 개수
v=int(input()) # 연결선 개수

graph = [[] for i in range(n+1)] # 그래프 초기화
visited=[False] * (n+1) # 방문한 컴퓨터인지 표시

for i in range(v): # 그래프 생성
    a,b=map(int,input().split())
    graph[a].append(b) # a에 b 연결
    graph[b].append(a) # b에 a 연결 -> 양방향

def dfs(v):
    visited[v]=1
    for nx in graph[v]:
        if not visited[nx]:
            dfs(nx)
dfs(1)
print(sum(visited)-1)