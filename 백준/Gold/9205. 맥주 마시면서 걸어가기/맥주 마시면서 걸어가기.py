from collections import deque
import sys
input = sys.stdin.readline

def bfs():
    q = deque()
    q.append((home_x, home_y))

    while q:
        x, y = q.popleft()
        # 맥주 20개로 페스티발에 도착할 수 있을 경우 happy 반환 후 종료
        if abs(x - festival_x) + abs(y - festival_y) <= 1000: # (맨해튼 거리)
            print("happy")
            return
        # 한번에 도착할 수 없을 경우, 편의점 방문
        for i in range(n): 
            if not visited[i]:
                new_x, new_y = market[i]
                if abs(x - new_x) + abs(y - new_y) <= 1000: # (맨해튼 거리)
                    q.append((new_x, new_y)) # 편의점의 위치부터 다시 시작
                    visited[i] = 1

    print("sad")


t = int(input())
for i in range(t):
    n = int(input())
    home_x,home_y = map(int, input().split())
    market = []
    for _ in range(n):
        maeket_x, market_y = map(int, input().split())
        market.append((maeket_x, market_y))
    festival_x, festival_y = map(int, input().split())
    visited = [False] * (n + 1)
    bfs()