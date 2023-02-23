def canVisitAllRooms(rooms):
    visited = [False] * len(rooms)
    visited[0] = True

    def dfs(cur_room):
        visited[cur_room] = True
        for key in rooms[cur_room]:
            if not visited[key]:
                dfs(key)
            
    dfs(0)

    if False in visited:
        return False
    return True

print(canVisitAllRooms(rooms = [[1,3],[3,0,1],[2],[0]]))
print(canVisitAllRooms(rooms = [[1],[2],[3],[]]))