def solution(record):
    answer = []
    dict = {}
    
    for text in record:
        info = text.split(" ")
        if info[0] == "Enter":
            dict[info[1]] = info[2]
        elif info[0] == "Change":
            dict[info[1]] = info[2]

    for text in record:
        info = text.split(" ")
        if info[0] == "Enter":
            answer.append("{}님이 들어왔습니다.".format(dict[info[1]]))
        elif info[0] == "Leave":
            answer.append("{}님이 나갔습니다.".format(dict[info[1]]))

    return answer