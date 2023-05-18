import sys
input = sys.stdin.readline

n = int(input())
tree = list(map(int, input().split())) 
delete = int(input())

def dfs(del_node):
    tree[del_node] = -10 # 의미없는 숫자를 부여해 제거함을 의미
    for i in range(n):
        if del_node == tree[i]: # tree[i]가 del_node의 자식이면 재귀를 통해 삭제
            dfs(i)
dfs(delete)
cnt = 0
for i in range(n):
    if tree[i] != -10 and i not in tree: # 제거된 노드가 아니고 i의 자식이 없다면
        cnt += 1

print(cnt)