graph = {
    'A': ['B', 'D', 'E'],
    'B': ['A', 'C', 'D'],
    'C': ['B'],
    'D': ['A', 'B'],
    'E': ['A']
}

visited = []

def dfs(cur_Node):
    visited.append(cur_Node)
    for val in graph[cur_Node]:
        if val not in visited:
            dfs(val)

print(graph.values)
print(dfs("A"))

