def solution(m, musicinfos):
    answer = []
    m = tranceCode(m)
    
    for i, music in enumerate(musicinfos):
        info = music.split(",")
        time = caltime(info[0], info[1])
        title = info[2]
        code = tranceCode(info[3])
        
        if len(code) > time:
            melody = code[0:time]
        else:
            a = time // len(code)
            b = time % len(code)
            melody = code * a + code[0:b]
            
        if m in melody:
            answer.append([time, i, info[2]]) # 정렬 조건을 위해 time, i도 같이 추가
    
    if answer:
        answer = sorted(answer, key=lambda x: (-x[0], x[1])) # 재생된 시간이 긴 음악, 먼저 입력된 음악 순 정렬
        return answer[0][2]
    else:
        return "(None)"

# 시간의 차이 계산
def caltime(start, end):
    hour = int(end.split(":")[0]) - int(start.split(":")[0])
    mdiff = 60 * hour + int(end.split(":")[1]) - int(start.split(":")[1])
    return mdiff
    
# "#"이 붙은 코드를 한 글자로 변환
def tranceCode(code):
    code = code.replace("C#", "c")
    code = code.replace("D#", "d")
    code = code.replace("F#", "f")
    code = code.replace("G#", "g")
    code = code.replace("A#", "a")
    return code
    
    