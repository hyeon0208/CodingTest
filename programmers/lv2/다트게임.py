def solution(dartResult):
    answer = 0
    score = []
    point = {"S" : 1, "D" : 2, "T" : 3}
    dart = []
    for i in dartResult.replace('10','k'):
        if i == "k":
            dart.append("10")
        else:
            dart.append(i)

    for i, d in enumerate(dart):
        if d in point:
            score.append(int(dart[i - 1]) ** point[d])
        elif d not in point and not d.isalnum():
            if d == "*":
                if i == 2:
                    score[0] *= 2
                if i == 4 or i == 5:
                    score[0] *= 2
                    score[1] *= 2
                if i == len(dartResult) - 1:
                    score[1] *= 2
                    score[2] *= 2
            else:
                if i == 2:
                    score[0] *= -1
                if i == 4 or i == 5:
                    score[1] *= -1
                if i == len(dartResult) - 1:
                    score[2] *= -1
    return sum(score)