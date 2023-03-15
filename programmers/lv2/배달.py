import heapq as hq

def dijkstra(dist, graph):
    heap = []
    hq.heappush(heap, [1, 0])
    while heap:
        node, cost = hq.heappop(heap)
        for n, c in graph[node]:
            if cost + c < dist[n]:
                dist[n] = cost + c
                hq.heappush(heap, [n, cost + c])

def solution(N, road, K):
    answer = 0
    dist = [float('inf')] * (N + 1)
    dist[1] = 0
    graph = [[] for _ in range(N + 1)]

    for r in road:
        graph[r[0]].append([r[1], r[2]])
        graph[r[1]].append([r[0], r[2]])                
    dijkstra(dist, graph)

    return len([i for i in dist if i <= K]) # K 시간 이하인 값들의 수를 반환