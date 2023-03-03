def solution(babbling):
    answer = 0
    speak = ["aya", "ye", "woo", "ma"]    
    
    for word in babbling:
        for s in speak:
            if s * 2 not in word:
                word = word.replace(s, " ")
        if word.strip() == "":
            answer += 1
    
    return answer