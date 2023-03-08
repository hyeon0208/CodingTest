def solution(p):
    # 입력이 빈 문자열인 경우 빈 문자열을 반환
    if not p:
        return ""
    
    answer = ""
    u, v = divideUV(p)
    
    if isCollect(u):
        return u + solution(v)
    else:
        answer += '('
        answer += solution(v)
        answer += ')'
        
        for c in u[1:len(u) - 1]:
            if c == '(':
                answer += ')'
            else:
                answer += '('
    return answer

# 올바른 괄호 문자열인지 확인
def isCollect(u):
    stack = []
    
    for c in u:
        if c == '(':
            stack.append(")")
        elif not stack or stack.pop() != c:
            return False
    return True

# 문자열 w를 u, v로 분리
def divideUV(p):
    left = 0
    right = 0
    
    for i in range(len(p)):
        if p[i] == '(':
            left += 1
        else:
            right += 1
        if left == right:
            return p[:i + 1], p[i + 1:]