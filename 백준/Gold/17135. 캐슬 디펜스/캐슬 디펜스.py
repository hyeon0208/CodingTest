import sys
input = sys.stdin.readline
from collections import deque
from itertools import combinations

N, M, D = map(int, input().split())

matrix = []
for _ in range(N):
    line = list(map(int, input().split()))
    matrix.append(line)

dx = [0, -1, 0]
dy = [-1, 0, 1]


def kill(archor):
    temp_matrix = [x[:] for x in matrix]

    killed = [[0] * M for _ in range(N)]
    result = 0
    #적군 움직이는 턴 (한칸씩 내려가는거를 for문을 반대로 돌리는 거로 생각)
    for i in range(N-1, -1, -1):
        #이번턴 죽는 애(궁수들이 한번에 공격하니까)
        this_turn = []
        #각 궁수별로 bfs 돌리면서 사정거리 안 제일 가까운 적군 찾기
        for ay in archor:
            #첫 값은 궁수 바로 위 칸으로 넣어줌
            dq = deque([(i, ay, 1)])
            while dq:
                x, y, d = dq.popleft()
                if temp_matrix[x][y] == 1:
                    this_turn.append((x, y))
                    if killed[x][y] == 0:
                        killed[x][y] = 1
                        result += 1
                    break
                if d < D:
                    for di in range(3):
                        nx = x + dx[di]
                        ny = y + dy[di]
                        if 0 <= nx < N and 0 <= ny < M:
                            dq.append((nx, ny, d+1))
        #한 턴에 공격한 애들 한번에 죽이기
        for x, y in this_turn:
            temp_matrix[x][y] = 0
                
    return result

answer = 0

archor_pos = list(combinations([i for i in range(M)], 3))
for a in archor_pos:
    answer = max(answer, kill(a))

print(answer)