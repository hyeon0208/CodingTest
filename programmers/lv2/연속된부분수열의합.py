def solution(sequence, k):
    n = len(sequence)

    max_sum = 0
    end = 0

    res = []
    for start in range(n):
        while max_sum < k and end < n:
            max_sum += sequence[end]
            end += 1
            
        if max_sum == k:
            res.append([start, end - 1, end - 1 - start])
            
        max_sum -= sequence[start]
        
    res = sorted(res, key=lambda x: x[2])

    return res[0][:2]