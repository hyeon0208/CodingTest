def solution(keymap, targets):
    answer = []
    dict = {}
    
    for keys in keymap:
        for i, k in enumerate(keys):
            if k not in dict:
                dict[k] = i + 1
            else:
                dict[k] = min(dict[k], i + 1)
                
    for target in targets:
        cnt = 0
        for k in target:
            if k not in dict:
                cnt = -1
                break
            cnt += dict[k]
        answer.append(cnt)
    
    return answer