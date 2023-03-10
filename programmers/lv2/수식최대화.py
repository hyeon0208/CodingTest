import re
from itertools import permutations

def solution(expression):
    answer = 0
    operator = set(re.findall("\D", expression)) # expression에 포함된 연산자만을 추출 (중복x)
    priorities_operators = list(permutations(operator, len(operator)))

    for priorities in priorities_operators:
        evaluated_val = int(dfs(expression, priorities, 0))
        answer = max(answer, abs(evaluated_val))

    return answer

def dfs(expression, priorities, depth):
    if depth == len(priorities) - 1:
        return str(eval(expression))
    
    oper = priorities[depth]
    print(expression.split(oper))
    evaluated = eval(
        oper.join(
            [dfs(e, priorities, depth = depth + 1) for e in expression.split(oper)]
        )
    )
    
    return str(evaluated)