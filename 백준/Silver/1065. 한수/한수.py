n = int(input())
cnt = 0

for i in range(1, n + 1):
    if i < 100:
        cnt += 1
    if 100 <= i < 1000:
        sep = list(map(int, str(i)))
        if sep[0] - sep[1] == sep[1] - sep[2]:
            cnt += 1

print(cnt)