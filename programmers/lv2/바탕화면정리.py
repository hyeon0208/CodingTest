def solution(wall):
    row = []
    col = []
    for i in range(len(wall)):
        for j in range(len(wall[0])):
            if wall[i][j] == "#":
                row.append(i)
                col.append(j)
    return [min(row), min(col), max(row) + 1, max(col) + 1]