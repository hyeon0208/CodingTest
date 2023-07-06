import sys
input = sys.stdin.readline

n = int(input())

# 퀸이 공격을 받는지 확인
def attack(x):
    for i in range(x): 
        # 같은 행에 퀸이있거나 대각선에 퀸이 있는 경우
        if row[x] == row[i] or abs(row[x] - row[i]) == abs(x - i):
            return True
    return False

def dfs(start):
    global cnt

    if start == n: # 마지막까지 탐색했을 경우
        cnt += 1
    else:
        for i in range(n):
            row[start] = i
            if not attack(start): # 퀸의 위협을 받지 않는다면 다음 탐색
                dfs(start + 1)

row = [0] * n # row[i]는 i행에 퀸이 있다는 의미
cnt = 0
dfs(0)

print(cnt)