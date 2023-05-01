answer = 0
visited = []

def solution(k, dungeons):
    dun_num = len(dungeons)
    visited = [False] * len(dungeons)
    
    def dfs(k, dungeons, depth):
        global answer
        answer = max(answer, depth)

        for i in range(len(dungeons)):
            if not visited[i] and dungeons[i][0] <= k:
                visited[i] = True
                dfs(k - dungeons[i][1], dungeons, depth + 1)
                visited[i] = False
    dfs(k, dungeons, 0)
    
    return answer