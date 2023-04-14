def solution(wall):
    x = []
    y = []
    for i in range(len(wall)):
        for j in range(len(wall[0])):
            if wall[i][j] == "#":
                x.append(i)
                y.append(j)
    return [min(x), min(y), max(x) + 1, max(y) + 1]