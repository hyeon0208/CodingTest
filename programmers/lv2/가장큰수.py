import functools

def comparator(a, b):
    t1 = a + b
    t2 = b + a
    
    if t1 > t2:
        return 1
    elif t1 == t2:
        return 0
    else:
        return -1

def solution(numbers):
    n = sorted(map(str, numbers), key = functools.cmp_to_key(comparator), reverse = True)
    answer = str(int(''.join(n)))
    return answer