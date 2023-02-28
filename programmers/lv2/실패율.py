def solution(N, stages):
    answer = {}
    leng = len(stages)
    
    for stage in range(1, N + 1):
        if stage in stages:
            cnt = stages.count(stage)
            answer[stage] = cnt / leng
            leng -= cnt
        else:
            answer[stage] = 0
    return sorted(answer, key = lambda x : answer[x], reverse = True)