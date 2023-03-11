def solution(keymap, targets):
    answer = []
    keymap_redesign = {}
    for k in keymap:
        k = list(k)
        for i in range(len(k)):
            try:
                if keymap_redesign[k[i]] > i+1:
                    keymap_redesign[k[i]] = i+1
            except:
                keymap_redesign[k[i]] = i+1
    for t in targets:
        t = list(t)
        _tmp = 0
        is_fail = False
        for tt in t:
            try:
                _tmp += keymap_redesign[tt]
            except:
                is_fail = True
                break
        if is_fail == False:
            answer.append(_tmp)
        else:
            answer.append(-1)
    return answer
