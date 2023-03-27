def solution(s):
    answer = []
    if len(s) == 1:
        return 1
    
    for i in range(1, len(s)//2 + 1):
        zip = ""
        cnt = 1
        temp = s[:i]

        for j in range(i, len(s) + i, i):
            if temp == s[j:i+j]:
                cnt += 1
            else:
                if cnt > 1:
                    zip = zip + str(cnt) + temp
                else:
                    zip = zip + temp
                temp = s[j:i+j]
                cnt = 1
        answer.append(len(zip))
        
    return min(answer)