import math

def solution(k, d):
    answer = 0
    
    for i in range(0, d + 1, k):
        # 원의 방정식 : x^2 + y^2 = d^2  => y^2 = d^2 - x^2
        result = math.floor(math.sqrt(d*d - i*i)) 
        # y가 k의 배수이므로 y를 k만큼 나누게 된다면 거리 d이하의 좌표 개수를 구할 수 있다. 
        answer += (result // k) + 1
    return answer