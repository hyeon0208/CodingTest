from collections import deque
import sys
input = sys.stdin.readline

def applyCommand(n, command):
    if command == "D":
        return (n * 2) % 10000

    if command == "S":
        return (n - 1) % 10000

    if command == "L":
        return (10 * n + (n // 1000)) % 10000

    if command == "R":
        return (((n % 10) * 1000) + (n // 10)) % 10000

def bfs(n):
    q = deque()
    q.append((n, ""))
    visited[n] = True

    while q:
        n, result = q.popleft()

        if n == b:
            return result

        for command in commands:
            newNum = applyCommand(n, command)

            if not visited[newNum]:
                visited[newNum] = True
                q.append((newNum, result + command))

t = int(input())
commands = ["D", "S", "L", "R"]

for _ in range(t):
    a, b = map(int, input().split())
    visited = [False] * 10000

    print(bfs(a))