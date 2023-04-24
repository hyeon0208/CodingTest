def ubak(k):
    result = []
    while k != 1:
        result.append(k)
        k = k / 2 if k % 2 == 0 else k * 3 + 1
    result.append(k)
    return result

def solution(k, ranges):
    answer = []
    collatz = ubak(k)

    for r in ranges:
        total = 0
        arrY = collatz[r[0] : len(collatz)+r[1]]
        
        if r[0] >= r[1] + len(collatz):
            answer.append(-1)
            continue
            
        for i in range(len(arrY) - 1):
            # 사다리꼴 넓이 구하는 공식 : ((윗변+아랫변) * 높이) / 2
            total += (((arrY[i] + arrY[i+1]) * 1) / 2)
        answer.append(total)
        
    return answer