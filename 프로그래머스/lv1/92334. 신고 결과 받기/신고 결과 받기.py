from collections import defaultdict

def solution(id_list, report, k):
    answer = []
    
    # 중복 신고 제거
    report = list(set(report))
    # user별 신고한 id
    user = defaultdict(set)
    # user별 신고당한 횟수
    cnt = defaultdict(int)
        
    for r in report:
        reporter, declaration = r.split(" ")
        user[reporter].add(declaration)
        cnt[declaration] += 1

    for i in id_list:
        result = 0
        for u in user[i]:
            if cnt[u] >= k:
                result += 1
        answer.append(result)
    return answer