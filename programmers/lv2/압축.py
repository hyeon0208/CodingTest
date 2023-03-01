def solution(msg):
    answer = []
    dict = {chr(i + ord('A')) : i + 1 for i in range(26)}
    
    start = 0
    for end in range(len(msg)):
        if msg[start : end + 1] not in dict:
            dict[msg[start : end + 1]] = len(dict) + 1
            answer.append(dict[msg[start : end]])
            start = end
    answer.append(dict[msg[start : len(msg)]])
    
    return answer
print(solution("KAKAO"))