graph = {
    'A': ['B', 'D', 'E'],
    'B': ['A', 'C', 'D'],
    'C': ['B'],
    'D': ['A', 'B'],
    'E': ['A']
}

from collections import deque

def bfs(graph, start):
    visited = [start]
    q = deque(start)
    while q:
        cur_Node = q.popleft()
        for val in graph[cur_Node]:
            if val not in visited:
                visited.append(val)
                q.append(val)
    return visited

print(bfs(graph, 'A'))