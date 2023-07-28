s = input()
result = []

for i in range(len(s)):
    result.append(s[i:])

result.sort()

for r in result:
    print(r)
