import sys
input = sys.stdin.readline

N, M = map(int, input().split())
N_arr = list(map(int, input().split()))
B_arr = list(map(int, input().split()))

result = N_arr + B_arr
result.sort()
result = ' '.join(map(str, result))
print(result)