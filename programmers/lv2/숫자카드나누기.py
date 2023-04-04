def solution(arrayA, arrayB):
    answer = 0
    
    # array의 첫 번째 값이 최대공약수로 가정 해 모든 요소와 비교하기 위해 아래처럼 초기화
    gcdA = arrayA[0]
    gcdB = arrayB[0]
    
    for n in arrayA[1:]:
        gcdA = gcd(n, gcdA)
        
    for n in arrayB[1:]:
        gcdB = gcd(n, gcdB)
        
    # 첫 번째 조건
    if notDiv(arrayA, gcdB):
        answer = max(answer, gcdB)
    
    # 두 번째 조건
    if notDiv(arrayB, gcdA):
        answer = max(answer, gcdA)
        
    return answer

# 최대공약수
def gcd(a, b):
    if a % b == 0:
        return b
    return gcd(b, a % b)

# 나누어떨어지는지
def notDiv(array, gcd):
    for n in array:
        if n % gcd == 0:
            return False
    return True