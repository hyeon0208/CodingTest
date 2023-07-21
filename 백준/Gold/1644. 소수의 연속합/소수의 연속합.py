import sys
input = sys.stdin.readline

N = int(input())

# 소수 배열
primes = [True] * (N + 1)

# 0과 1은 소수가 아니므로 False
primes[0] = False
primes[1] = False

# 소수 구하기 (에라토스테네스의 체 알고리즘)
for i in range(2, int(N ** 0.5) + 1):
    if primes[i]:
        for j in range(i + i, N + 1, i):
            primes[j] = False


primes = [i for i in range(2, N+1) if primes[i]]

# 투 포인터
start = 0
end = 0
total = 0
cnt = 0

while True:
    if total == N:
        cnt += 1
        
    if total > N:
        total -= primes[start]
        start += 1
    elif end == len(primes):
        break
    else:
        total += primes[end]
        end += 1
        
print(cnt)
